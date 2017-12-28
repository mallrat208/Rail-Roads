package com.mr208.railroads.common.network;

import com.mr208.railroads.client.ClientEvents;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketRailRidersCooldown implements IMessage
{
	public PacketRailRidersCooldown() {};

	@Override
	public void fromBytes(ByteBuf buf)
	{

	}

	@Override
	public void toBytes(ByteBuf buf)
	{

	}

	public static class Handler implements IMessageHandler<PacketRailRidersCooldown, IMessage>
	{

		@Override
		public IMessage onMessage(PacketRailRidersCooldown message, MessageContext ctx)
		{
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
			return null;
		}

		private void handle(PacketRailRidersCooldown message, MessageContext ctx)
		{
			Minecraft mc = Minecraft.getMinecraft();

			if(mc.player != null)
			{
				ClientEvents.INSTANCE.resetHotkeyTimeout();
			}
		}
	}
}
