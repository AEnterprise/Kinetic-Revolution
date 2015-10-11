package kineticrevolution.core;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import kineticrevolution.lib.Reference;

@IFMLLoadingPlugin.Name(Reference.MOD_NAME + "-CorePlugin")
@IFMLLoadingPlugin.MCVersion(Reference.MC_VERSION)
public class CorePlugin implements IFMLLoadingPlugin {

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{"kineticrevolution.util.ASMInjector"};
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {

	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}
