package com.mr208.railroads;

import com.mr208.railroads.common.CommonProxy;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = RailRoads.MOD_ID, name = RailRoads.MOD_NAME, version = RailRoads.MOD_VERS)
public class RailRoads
{
	public static final String MOD_ID = "railroads";
	public static final String MOD_NAME = "Rail Roads";
	public static final String MOD_VERS = "1.0.0";

	public static final String RAIL_RIDER_TAG = "RailRiders";

	private static final String COMMON_PROXY = "com.mr208.railroads.common.CommonProxy";
	private static final String CLIENT_PROXY = "com.mr208.railroads.client.ClientProxy";

	@SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		RailRoads.proxy.onPreInit();
	}

	@Mod.EventHandler
	public void onInit(FMLInitializationEvent event)
	{
		RailRoads.proxy.onInit();
	}

	public static boolean hasSkateTag(ItemStack boots)
	{
		if(boots.hasTagCompound() && boots.getTagCompound().hasKey(RAIL_RIDER_TAG))
		{
			return boots.getTagCompound().getBoolean(RAIL_RIDER_TAG);
		}

		return false;
	}
}
