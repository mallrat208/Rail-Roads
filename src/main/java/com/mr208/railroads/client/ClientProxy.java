package com.mr208.railroads.client;

import com.mr208.railroads.common.CommonProxy;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{

	@Override
	public void onInit()
	{
		super.onInit();
		KeyHandler.init();
		MinecraftForge.EVENT_BUS.register(ClientEvents.INSTANCE);
	}
}
