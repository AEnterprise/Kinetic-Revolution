package info.aenterprise.kineticrevolution.core;

import info.aenterprise.kineticrevolution.utils.FluidUtils;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class EventListener {

	@SubscribeEvent
	public void fluidRegistered(FluidRegistry.FluidRegisterEvent event) {
		System.out.println(event.fluidName);
		FluidUtils.registerFluidContainers(FluidRegistry.getFluid(event.fluidName));
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void loadTextures(TextureStitchEvent.Post event) {
		FluidUtils.init(event.map);
	}
}
