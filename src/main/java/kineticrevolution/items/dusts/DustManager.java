package kineticrevolution.items.dusts;

import cpw.mods.fml.common.registry.GameRegistry;
import kineticrevolution.recipes.DusterOutput;
import kineticrevolution.recipes.DusterRecipeManager;
import kineticrevolution.recipes.IDusterRecipe;
import kineticrevolution.util.OreDictHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by AEnterprise
 */
public class DustManager {
	static final HashMap<Integer, Dust> registeredDusts = new HashMap<Integer, Dust>();
	static final ArrayList<String> dusts = new ArrayList<String>();

	/**
	 * Should only ever be called once or things will break
	 * add new dusts on the bottom to avoid 'transmutations'
	 */
	public static void registerDusts() {
		registerMetal("Iron", 0xD2CEC9);
		registerMetal("Gold", 0xF8DF17);
		register("Emerald", 0x00B038);
		register("Coal", 0x1B1B1B);
		register("Charcoal", 0x53493A);
		register("Obsidian", 0x171124);
		register("EnderPearl", 0x105E51);
		register("NetherQuartz", 0xDBCCBF);
		registerMetal("Bronze", 0xAD6726);
		registerMetal("Manganese", 0xF3D2D2);
		registerMetal("Hepatizon", 0x6B566B);
		registerMetal("DamascusSteel", 0x3D2C1F);
		registerMetal("Angmallen", 0xFAFA6C);
		registerMetal("Steel", 0x919191);
		registerMetal("Eximite", 0x9E83B4);
		registerMetal("Meutoite", 0x5F5269);
		registerMetal("Desichalkos", 0x742EA8);
		registerMetal("Prometheum", 0x5A8156);
		registerMetal("DeepIron", 0x495B69);
		registerMetal("Infuscolium", 0xCB6293);
		registerMetal("BlackSteel", 0x395679);
		registerMetal("Oureclase", 0xDCA82E);
		registerMetal("AstralSilver", 0xC8D4D5);
		registerMetal("Carmot", 0xA99733);
		registerMetal("Mithril", 0x08B5C3);
		registerMetal("Rubracium", 0x8E2727);
		registerMetal("Quicksilver", 0x7CD3C7);
		registerMetal("Haderoth", 0xD1531E);
		registerMetal("Orichalcum", 0x547A38);
		registerMetal("Celenegil", 0x94CC48);
		registerMetal("Adamantine", 0xF04040);
		registerMetal("Atlarus", 0xF4D603);
		registerMetal("Tartarite", 0xFF763C);
		registerMetal("Ignatius", 0xE87400);
		registerMetal("ShadowIron", 0x8D7565);
		registerMetal("Lemurite", 0xEFEFEF);
		registerMetal("Midasium", 0xFFA826);
		registerMetal("Vyroxeres", 0x55E001);
		registerMetal("Ceruclase", 0x458FAB);
		registerMetal("Alduorite", 0xA3DEDE);
		registerMetal("Kalendrite", 0xAA5BBD);
		registerMetal("Vulcanite", 0xFF8448);
		registerMetal("Sanguinite", 0xB90000);
		registerMetal("ShadowSteel", 0x887362);
		registerMetal("Inolashite", 0x40AA7D);
		registerMetal("Amordrine", 0xA98DB1);
		registerMetal("Zinc", 0xDCDFA4);
		registerMetal("Brass", 0xD89634);
		registerMetal("Electrum", 0xDFD0AA);
		registerMetal("Aluminum", 0xEDEDED);
		registerMetal("Ardite", 0xF28900);
		registerMetal("Manyullyn", 0xAB7EE3);
		registerMetal("Cobalt", 0x0064FF);
		registerMetal("Copper", 0xBF5E1F);
		registerMetal("Lead", 0x808096);
		registerMetal("Nickel", 0xBAB0A4);
		registerMetal("Platinum", 0xABCDEF);
		registerMetal("Silver", 0xB3B3B3);
		registerMetal("Tin", 0xF2F2F2);
		registerMetal("Osmium", 0x92A6B8);
		registerMetal("Sulfur", 0xFFDB50);
		registerMetal("Saltpeter", 0xDEDEDE);
		registerMetal("CertusQuartz", 0xBAC5F2);
		registerMetal("AluminumBrass", 0xF0D467);
		registerMetal("Alumite", 0xF4CCEC);
		registerMetal("PigIron", 0xF0A8A4);
		registerMetal("Invar", 0x959E99);
		registerMetal("Signalum", 0xFF5B00);
		registerMetal("Lumium", 0xE7D648);
		registerMetal("EnderiumBase", 0x4B7A9A);
		registerMetal("Enderium", 0x0F7575);
		registerMetal("ElectricalSteel", 0x949494);
		registerMetal("EnergeticAlloy", 0xE47700);
		registerMetal("PhasedGold", 0xB2D44D);
		registerMetal("RedstoneAlloy", 0x942323);
		registerMetal("ConductiveIron", 0x945758);
		registerMetal("PhasedIron", 0x2E6C3D);
		registerMetal("DarkSteel", 0x4B4B4B);
		registerMetal("Soularium", 0x654D31);
		registerMetal("FzDarkIron", 0x6700BE);
		registerMetal("Yellorium", 0xD7EF00);
		registerMetal("Graphite", 0x515151);
		registerMetal("Cyanite", 0x0087EF);
		registerMetal("Blutonium", 0x1B00E6);
		registerMetal("Ludicrite", 0xEF00EF);
		registerMetal("Pyrotheum", 0xF8B33D);
		registerMetal("Cryotheum", 0x49EFFF);
		registerMetal("Blizz", 0x8BEDFC);
	}

	public static void register(String name, int color) {
		register(new Dust(name, color));
	}

	public static void registerMetal(final String name, int color) {
		register(new Dust(name, color) {
			@Override
			public boolean shouldRegister() {
				return ingotPresent(name);
			}

			@Override
			public ItemStack getSmeltingOutput() {
				return OreDictHelper.getFirstStack("ingot" + name);
			}
		});
		//ingot -> dust
		if (OreDictHelper.getFirstStack("ingot" + name) != null) {
			DusterRecipeManager.registerRecipe(new IDusterRecipe() {
				@Override
				public boolean validInput(ItemStack input) {
					int id = OreDictionary.getOreID("ingot" + name);
					for (int key : OreDictionary.getOreIDs(input)) {
						if (id == key)
							return true;
					}
					return false;
				}

				@Override
				public List<DusterOutput> getOutputs() {
					ArrayList<DusterOutput> list = new ArrayList<DusterOutput>();
					list.add(new DusterOutput(OreDictHelper.getFirstStack("ingot" + name), 100, 0, 0, 0, 0));
					return list;
				}
			});
		}
	}

	public static void register(Dust dust) {
		dusts.add(dust.name);
		if (dust.shouldRegister()) {
			registeredDusts.put(dusts.indexOf(dust.name), dust);
			if (dust.getSmeltingOutput() != null)
				GameRegistry.addSmelting(dust.getStack(), dust.getSmeltingOutput(), 0.5f);
		}
	}

	public static Dust getDust(int index) {
		return registeredDusts.get(index);
	}

	public static Dust getDust(String name) {
		return registeredDusts.get(dusts.indexOf(name));
	}

	public static boolean ingotPresent(String metalname) {
		return !OreDictHelper.lookup("ingot" + metalname).isEmpty();
	}
}
