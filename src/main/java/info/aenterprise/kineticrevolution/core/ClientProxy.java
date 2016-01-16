package info.aenterprise.kineticrevolution.core;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() {
		super.preInit();
		ModelLoader.setBucketModelDefinition(ItemLoader.canisterT1);
		final ModelResourceLocation withFluid = new ModelResourceLocation(new ResourceLocation("kineticrevolution", "canisterT1"), "inventory");
		ModelLoader.setCustomMeshDefinition(ItemLoader.canisterT1, new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return withFluid;
			}
		});
		ModelBakery.registerItemVariants(ItemLoader.canisterT1, withFluid);

		ModelLoader.setBucketModelDefinition(ItemLoader.canisterT2);
		final ModelResourceLocation withFluid1 = new ModelResourceLocation(new ResourceLocation("kineticrevolution", "canisterT2"), "inventory");
		ModelLoader.setCustomMeshDefinition(ItemLoader.canisterT2, new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return withFluid1;
			}
		});
		ModelBakery.registerItemVariants(ItemLoader.canisterT2, withFluid1);

		ModelLoader.setBucketModelDefinition(ItemLoader.canisterT3);
		final ModelResourceLocation withFluid2 = new ModelResourceLocation(new ResourceLocation("kineticrevolution", "canisterT3"), "inventory");
		ModelLoader.setCustomMeshDefinition(ItemLoader.canisterT3, new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return withFluid2;
			}
		});
		ModelBakery.registerItemVariants(ItemLoader.canisterT3, withFluid2);

	}

	@Override
	public void init() {
		super.init();
		OBJLoader.instance.addDomain("kineticrevolution");
	}

	@Override
	public void registerInventoryModel(Block block, int meta) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}

	@Override
	public void registerInventoryModel(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
