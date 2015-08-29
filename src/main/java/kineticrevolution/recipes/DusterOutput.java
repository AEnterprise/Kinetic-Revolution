package kineticrevolution.recipes;

import net.minecraft.item.ItemStack;

/**
 * Created by AEnterprise
 */
public class DusterOutput {
	public final ItemStack output;
	//the chance of this output to actually generate in %, can't be more then 100
	public final int baseChance;
	//will be added to the base chance in this duster
	public final int t1increase;
	public final int t2increase;
	public final int t3increase;
	public final int t4increase;

	public DusterOutput(ItemStack output, int baseChance, int t1increase, int t2increase, int t3increase, int t4increase) {
		this.output = output;
		this.baseChance = baseChance;
		this.t1increase = t1increase;
		this.t2increase = t2increase;
		this.t3increase = t3increase;
		this.t4increase = t4increase;
	}
}
