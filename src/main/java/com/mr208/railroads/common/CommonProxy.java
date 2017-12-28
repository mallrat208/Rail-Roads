package com.mr208.railroads.common;

import net.minecraftforge.common.MinecraftForge;

public class CommonProxy
{
	public void onPreInit()
	{
		RRContent.onPreInit();

		MinecraftForge.EVENT_BUS.register(EventHandler.INSTANCE);
	}

	public void onInit()
	{
		NetworkHandler.onInit();
	}
}
