package kineticrevolution.util;

import com.google.common.base.Strings;
import com.mojang.authlib.GameProfile;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;
import java.util.UUID;

/**
 * Created by AEnterprise
 */
public class Utils {

	public static final GameProfile FAKEPLAYER_USER_PROFILE = new GameProfile(UUID.nameUUIDFromBytes("[Kinetic Revolution]".getBytes()), "[Kinetic Revolution]");

	public static String localize(String key) {
		return ("" + StatCollector.translateToLocal(key)).trim();
	}

	public static String localizeFormatted(String key, Object... objects) {
		return ("" + StatCollector.translateToLocalFormatted(key, objects)).trim();
	}

	public static String localizeAllFormatted(String key, String... strings) {
		Object[] objects = new Object[strings != null ? strings.length : 0];
		if (strings != null) {
			for (int i = 0; i < objects.length; i++) {
				String string = strings[i];
				if (string != null)
					objects[i] = localize(string);
			}
		}
		return localizeFormatted(key, objects);
	}

	public static String decapitalizeFirstChar(String string) {
		return !Strings.isNullOrEmpty(string) ? Character.toLowerCase(string.charAt(0)) + string.substring(1) : null;
	}


	public static void dropItemstack(World world, double x, double y, double z, ItemStack stack) {
		if (!world.isRemote && stack != null && stack.stackSize > 0 && stack.getItem() != null) {
			float rx = world.rand.nextFloat() * 0.8F + 0.1F;
			float ry = world.rand.nextFloat() * 0.8F + 0.1F;
			float rz = world.rand.nextFloat() * 0.8F + 0.1F;
			EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, stack.copy());
			float factor = 0.05F;
			entityItem.motionX = (world.rand.nextGaussian() * factor);
			entityItem.motionY = (world.rand.nextGaussian() * factor + 0.2000000029802322D);
			entityItem.motionZ = (world.rand.nextGaussian() * factor);
			world.spawnEntityInWorld(entityItem);
		}
	}

	public static void dropItemstacks(World world, double x, double y, double z, List<ItemStack> stacks) {
		if (stacks != null && !stacks.isEmpty()) {
			for (ItemStack stack : stacks)
				dropItemstack(world, x, y, z, stack);
		}

	}

	public static boolean harvestBlock(World world, int x, int y, int z, EntityPlayer player) {
		if (player == null) {
			player = FakePlayerFactory.get(Minecraft.getMinecraft().getIntegratedServer().worldServerForDimension(world.provider.dimensionId), FAKEPLAYER_USER_PROFILE);
			player.setPosition(x, y, z);
		}
		if (world.isAirBlock(x, y, z))
			return false;
		EntityPlayerMP playerMP = null;
		if (player instanceof EntityPlayerMP)
			playerMP = (EntityPlayerMP) player;
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		if (!ForgeHooks.canHarvestBlock(block, player, meta))
			return false;
		if (playerMP != null) {
			BlockEvent.BreakEvent event = ForgeHooks.onBlockBreakEvent(world, playerMP.theItemInWorldManager.getGameType(), playerMP, x, y, z);
			if (event.isCanceled())
				return false;
		}
		if (player.capabilities.isCreativeMode) {
			if (!world.isRemote)
				block.onBlockHarvested(world, x, y, z, meta, player);
			else
				world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) | (meta << 12));
			if (block.removedByPlayer(world, player, x, y, z, false))
				block.onBlockDestroyedByPlayer(world, x, y, z, meta);
			if (!world.isRemote && playerMP != null && !(playerMP instanceof FakePlayer))
				playerMP.playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
			else
				Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C07PacketPlayerDigging(2, x, y, z, Minecraft.getMinecraft().objectMouseOver.sideHit));
			return true;
		}

		if (!world.isRemote) {
			block.onBlockHarvested(world, x, y, z, meta, player);
			if (block.removedByPlayer(world, player, x, y, z, true)) {
				block.onBlockDestroyedByPlayer(world, x, y, z, meta);
				block.harvestBlock(world, player, x, y, z, meta);
			}
			if (playerMP != null && !(playerMP instanceof FakePlayer))
				playerMP.playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
		} else {
			//world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) | (meta << 12));
			if (block.removedByPlayer(world, player, x, y, z, true))
				block.onBlockDestroyedByPlayer(world, x, y, z, meta);
			Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C07PacketPlayerDigging(2, x, y, z, Minecraft.getMinecraft().objectMouseOver.sideHit));
		}
		return true;
	}

	public static boolean areItemStacksEqual(ItemStack stack1, ItemStack stack2) {
		return stack1 != null && stack2 != null && stack1.getItem() == stack2.getItem() && ItemStack.areItemStackTagsEqual(stack1, stack2) && stack1.getItemDamage() == stack2.getItemDamage();
	}
}
