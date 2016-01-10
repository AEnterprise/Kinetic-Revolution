package info.aenterprise.kineticrevolution.blocks;

import info.aenterprise.kineticrevolution.core.KineticRevolution;
import info.aenterprise.kineticrevolution.tileEntities.TileEntityFluidicCompressor;
import info.aenterprise.kineticrevolution.utils.GuiIDs;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class BlockFluidicCompressor extends BlockContainer {
	public static final PropertyDirection FACING = PropertyDirection.create("FACING");

	public BlockFluidicCompressor() {
		super("fluidicCompressor");
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote)
			player.openGui(KineticRevolution.INSTANCE, GuiIDs.FLUIDIC_COMPRESSOR, world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityFluidicCompressor();
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		world.setBlockState(pos, state.withProperty(FACING, EnumFacing.getFacingFromVector((float) (placer.posX - pos.getX()), 0, (float) (placer.posZ - pos.getZ()))));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, FACING);
	}
}
