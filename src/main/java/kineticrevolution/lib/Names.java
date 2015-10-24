package kineticrevolution.lib;

public class Names {

	public static class Items {

		public static final String MACHINE_CONFIGURATOR = "machineConfigurator";
		public static final String RESOURCE = "resource";
		public static final String DUST = "dust";

		public static class Resources {

			public static final String GRINDING_WHEEL = "grindingWheel";
			public static final String SPRING_TIN = "springTin";
			public static final String SPRING_SILVER = "springSilver";
			public static final String SPRING_LEAD = "springLead";
			public static final String SPRING_IRON = "springIron";
			public static final String SPRING_GOLD = "springGold";
			public static final String SPRING_ENDERIUM = "springEnderium";
			public static final String SPRING_ELECTRUM = "springElectrum";
			public static final String SPRING_DIAMOND = "springDiamond";
			public static final String SPRING_COPPER = "springCopper";
			public static final String SPRING_BRONZE = "springBronze";
		}

	}

	public static class Blocks {

		public static final String GENERATOR_CASING = "generatorCasing";
		public static final String GENERATOR_INDUCTOR = "generatorInductor";

		public static final String DUSTER = "duster";

	}

	public static class TileEntities {

		public static final String MULTIBLOCK_BASE = "tileMultiBlockBase";
		public static final String MULTIBLOCK_GENERATOR = "tileKineticGenerator";
		public static final String DUSTER = "tileDuster";

	}

	public static class Recipes {

		public static final String DUSTING = "dusting";

	}

	public static class GUIs {

		public static class NEI {

			public static final String DUSTING = "gui." + Reference.MOD_ID + ".nei." + Recipes.DUSTING + ".name";

		}

	}

	public static class CTabs {

		public static final String MAIN_TAB = Reference.MOD_ID;
		public static final String DUST_TAB = "krDusts";

	}

}