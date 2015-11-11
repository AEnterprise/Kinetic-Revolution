package kineticrevolution.inventories.containers;

import kineticrevolution.core.recipes.AssemblerRecipes;
import kineticrevolution.inventories.Inventory;
import kineticrevolution.inventories.slots.SlotInput;
import kineticrevolution.inventories.slots.SlotOutput;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;

/**
 * Created by AEnterprise
 */
public class ContainerAssembler extends ContainerCrafting {
	private Inventory inventory;

	public ContainerAssembler(InventoryPlayer inventoryPlayer, Inventory inventory) {
		super(inventoryPlayer, inventory);
		this.inventory = inventory;
		addSlotToContainer(new SlotInput(inventory, 0, 25, 35, this));
		addSlotToContainer(new SlotInput(inventory, 1, 62, 17, this));
		addSlotToContainer(new SlotInput(inventory, 2, 80, 17, this));
		addSlotToContainer(new SlotInput(inventory, 3, 98, 17, this));
		addSlotToContainer(new SlotInput(inventory, 4, 62, 35, this));
		addSlotToContainer(new SlotInput(inventory, 5, 80, 35, this));
		addSlotToContainer(new SlotInput(inventory, 6, 98, 35, this));
		addSlotToContainer(new SlotInput(inventory, 7, 62, 53, this));
		addSlotToContainer(new SlotInput(inventory, 8, 80, 53, this));
		addSlotToContainer(new SlotInput(inventory, 9, 98, 53, this));
		addSlotToContainer(new SlotOutput(inventory, 10, 134, 35, this));
		addPlayerInventory(8, 84);
	}


	@Override
	public void onInputChanged() {
		ItemStack[] stacks = new ItemStack[10];
		for (int i = 0; i < 10; i++)
			stacks[i] = inventory.getStackInSlot(i);
		inventory.setInventorySlotContents(10, AssemblerRecipes.INSTANCE.getOutput(stacks));
	}

	@Override
	public void onCrafted() {
		for (int i = 1; i < 10; i++)
			inventory.decrStackSize(i, 1);
	}

	@Override
	public void addCraftingToCrafters(ICrafting p_75132_1_) {
		super.addCraftingToCrafters(p_75132_1_);
	}
}
