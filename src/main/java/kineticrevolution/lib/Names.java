package kineticrevolution.lib;

public class Names {

	public static class Items {

		public static final String MACHINE_CONFIGURATOR = "machineConfigurator";
		public static final String RESOURCE = "resource";
		public static final String DUST = "dust";

		public static class Resources {

			public static final String GRINDING_WHEEL = "grindingWheel";

		}

	}

	public static class Blocks {

		public static final String GENERATOR_CASING = "generatorCasing";
		public static final String GENERATOR_INDUCTOR = "generatorInductor";

	}

	public static class TileEntities {

		public static final String MULTIBLOCK_BASE = "tileMultiBlockTest";
		public static final String MULTIBLOCK_GENERATOR = "tileKineticGenerator";

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