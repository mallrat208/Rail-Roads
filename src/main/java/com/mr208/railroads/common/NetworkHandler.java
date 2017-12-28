package com.mr208.railroads.common;

import com.mr208.railroads.RailRoads;
import com.mr208.railroads.common.network.PacketRailRiders;
import com.mr208.railroads.common.network.PacketRailRidersCooldown;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler
{
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(RailRoads.MOD_ID);

	private static int packet = 0;

	public static void onInit()
	{
		NetworkHandler.INSTANCE.registerMessage(PacketRailRiders.Handler.class, PacketRailRiders.class, packet++, Side.SERVER);
		NetworkHandler.INSTANCE.registerMessage(PacketRailRidersCooldown.Handler.class, PacketRailRidersCooldown.class, packet++, Side.CLIENT);
	}
}
