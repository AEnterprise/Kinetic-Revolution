package info.aenterprise.kineticrevolution.utils;

import com.google.common.collect.Maps;
import info.aenterprise.kineticrevolution.core.ItemLoader;
import info.aenterprise.kineticrevolution.items.ItemCanister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class FluidUtils {
	private static int nextMeta = 1;
	private static final HashMap<Fluid, Integer> metas = new HashMap<Fluid, Integer>();

	public enum FluidType {
		FLOWING,
		STILL
	}
	public static final ResourceLocation BLOCK_TEXTURE = TextureMap.locationBlocksTexture;

	private static Map<FluidType, Map<Fluid, TextureAtlasSprite>> textureMap = Maps.newHashMap();
	private static TextureAtlasSprite missingIcon;


	public static void init(TextureMap map) {
		missingIcon = map.getMissingSprite();
		textureMap.clear();

		for (FluidType type : FluidType.values()) {
			textureMap.put(type, new HashMap<Fluid, TextureAtlasSprite>());
		}

		for (Fluid fluid : FluidRegistry.getRegisteredFluids().values()) {
			if (fluid.getFlowing() != null) {
				String flow = fluid.getFlowing().toString();
				TextureAtlasSprite sprite;
				if (map.getTextureExtry(flow) != null) {
					sprite = map.getTextureExtry(flow);
				} else {
					sprite = map.registerSprite(fluid.getStill());
				}
				textureMap.get(FluidType.FLOWING).put(fluid, sprite);
			}

			if (fluid.getStill() != null) {
				String still = fluid.getStill().toString();
				TextureAtlasSprite sprite;
				if (map.getTextureExtry(still) != null) {
					sprite = map.getTextureExtry(still);
				} else {
					sprite = map.registerSprite(fluid.getStill());
				}
				textureMap.get(FluidType.STILL).put(fluid, sprite);
			}
		}
	}

	public static TextureAtlasSprite getFluidTexture(Fluid fluid, FluidType type) {
		if (fluid == null || type == null) {
			return missingIcon;
		}
		Map<Fluid, TextureAtlasSprite> map = textureMap.get(type);
		return map.containsKey(fluid) ? map.get(fluid) : missingIcon;
	}

	public static void registerFluidContainers(Fluid fluid) {
		if (ItemLoader.canisterT1 == null)
			ItemLoader.addCanisters();
		metas.put(fluid, nextMeta);
		addFluidContainer(fluid, ItemLoader.canisterT1);
		addFluidContainer(fluid, ItemLoader.canisterT2);
		addFluidContainer(fluid, ItemLoader.canisterT3);
		nextMeta++;
	}

	private static void addFluidContainer(Fluid fluid, ItemCanister canister) {
		ItemStack stack = new ItemStack(canister, 1, nextMeta);
		FluidStack fluidstack = new FluidStack(fluid, canister.getCapacity(stack));
		NBTTagCompound tag = new NBTTagCompound();
		fluidstack.writeToNBT(tag);
		StackUtils.getTagCompound(stack).setTag("fluid", tag);
		FluidContainerRegistry.registerFluidContainer(fluid, stack, new ItemStack(canister));
		canister.addVariant(stack);
	}

	public static int getMetaForFluid(FluidStack fluid) {
		if (fluid == null)
			return 0;
		return metas.get(fluid.getFluid());
	}
}
