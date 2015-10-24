package kineticrevolution.items.blueprints;

import cpw.mods.fml.common.registry.GameRegistry;
import kineticrevolution.core.CTabs;
import kineticrevolution.lib.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by AEnterprise
 */
public class ItemBlueprint extends ItemMap {
	public ItemBlueprint() {
		setUnlocalizedName("blueprint");
		setTextureName(Reference.MOD_ID + ":" + "blueprint");
		setCreativeTab(CTabs.MAIN_TAB);
		GameRegistry.registerItem(this, "blueprint");
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int meta, boolean onHand) {

	}

	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {

	}

	@Override
	public Packet func_150911_c(ItemStack stack, World world, EntityPlayer player) {
		return null;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean sneak) {

	}
}
