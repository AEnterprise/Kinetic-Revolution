package info.aenterprise.kineticrevolution.tileEntities;

import cofh.api.energy.IEnergyReceiver;
import info.aenterprise.kineticrevolution.networking.SyncIDs;
import info.aenterprise.kineticrevolution.utils.FluidTank;
import info.aenterprise.kineticrevolution.utils.Inventory;
import info.aenterprise.kineticrevolution.utils.RFBattery;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fml.common.network.ByteBufUtils;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class TileEntityFluidicCompressor extends TileSyncBase implements ITickable, ISidedInventory, IFluidHandler, IEnergyReceiver {
	private final Inventory inventory = new Inventory(2, 64, this, "inventory");
	private final FluidTank tank = new FluidTank(this, "tank", 10000);
	private final RFBattery battery = new RFBattery(5000, 300, 300);

	@Override
	public void update() {

	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		inventory.readFromNBT(compound.getCompoundTag(inventory.tagName()));
		tank.readFromNBT(compound.getCompoundTag(tank.tagName()));
		battery.readFromNBT(compound.getCompoundTag(battery.tagName()));
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTTagCompound inventoryTag = new NBTTagCompound();
		inventory.saveToNBT(inventoryTag);
		compound.setTag(inventory.tagName(), inventoryTag);
		NBTTagCompound tankTag = new NBTTagCompound();
		tank.saveToNBT(tankTag);;
		compound.setTag(tank.tagName(), tankTag);
		NBTTagCompound batteryTag = new NBTTagCompound();
		battery.saveToNBT(batteryTag);
		compound.setTag(battery.tagName(), batteryTag);
	}

	@Override
	public int getSizeInventory() {
		return inventory.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory.getStackInSlot(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return inventory.decrStackSize(index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return inventory.removeStackFromSlot(index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		inventory.setInventorySlotContents(index, stack);
	}

	@Override
	public int getInventoryStackLimit() {
		return inventory.getInventoryStackLimit();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {

	}

	@Override
	public void closeInventory(EntityPlayer player) {

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return inventory.isItemValidForSlot(index, stack);
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {

	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {

	}

	@Override
	public String getName() {
		return inventory.getName();
	}

	@Override
	public boolean hasCustomName() {
		return inventory.hasCustomName();
	}

	@Override
	public IChatComponent getDisplayName() {
		return null;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return new int[] {0, 1};
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStack, EnumFacing direction) {
		return index == 0 && itemStack != null && itemStack.getItem() instanceof IFluidContainerItem; // only insert fluid containers in the input
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return index == 1; //only extract from the output slot
	}

	@Override
	public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
		return tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
		if (resource == null) return null;
		return tank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
		return tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(EnumFacing from, Fluid fluid) {
		return !tank.isFull() && (tank.getFluid() == null || tank.getFluid().getFluid() == fluid);
	}

	@Override
	public boolean canDrain(EnumFacing from, Fluid fluid) {
		return !tank.isEmpty() && tank.getFluid() != null && tank.getFluid().getFluid() == fluid;
	}

	@Override
	public FluidTankInfo[] getTankInfo(EnumFacing from) {
		return new FluidTankInfo[] {tank.getInfo()};
	}

	@Override
	public int getIdentifier() {
		return SyncIDs.FLUIDIC_COMPRESSOR.ordinal();
	}

	@Override
	public void writeToByteBuff(ByteBuf buf) {
		NBTTagCompound tag = new NBTTagCompound();
		tank.saveToNBT(tag);
		ByteBufUtils.writeTag(buf, tag);
	}

	@Override
	public void readFromByteBuff(ByteBuf buf) {
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		tank.readFromNBT(tag);
	}

	public FluidTank getTank() {
		return tank;
	}

	@Override
	public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
		return battery.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int getEnergyStored(EnumFacing from) {
		return battery.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from) {
		return battery.getMaxEnergyStored();
	}

	@Override
	public boolean canConnectEnergy(EnumFacing from) {
		return true;
	}
}
