package com.mr208.railroads.client;

import com.mr208.railroads.common.CommonProxy;
import com.mr208.railroads.common.RRContent;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{
	@Override
	public void onPreInit()
	{
		super.onPreInit();
		
		for(Item item : RRContent.registeredItems)
		{
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}

	@Override
	public void onInit()
	{
		super.onInit();
		KeyHandler.init();
		MinecraftForge.EVENT_BUS.register(ClientEvents.INSTANCE);
	}
}
