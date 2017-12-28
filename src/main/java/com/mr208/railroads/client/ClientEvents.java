package com.mr208.railroads.client;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.mr208.railroads.RailRoads;
import com.mr208.railroads.common.NetworkHandler;
import com.mr208.railroads.common.network.PacketRailRiders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class ClientEvents
{
	public static ClientEvents INSTANCE = new ClientEvents();
	private Minecraft mc = Minecraft.getMinecraft();
	private boolean hotkeyTimeout = false;

	public ClientEvents()
	{

	}

	public void resetHotkeyTimeout()
	{
		hotkeyTimeout = false;
	}

	@SubscribeEvent
	public void onToolTip(ItemTooltipEvent event)
	{
		if(RailRoads.hasSkateTag(event.getItemStack()))
		{
			event.getToolTip().add(ChatFormatting.AQUA + I18n.format("railroads.tooltip.installed"));
		}
	}

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event)
	{
		if(mc.player!= null && mc.currentScreen == null && !mc.player.isRiding())
		{
			if(!this.hotkeyTimeout && KeyHandler.skates.isPressed())
			{
				NetworkHandler.INSTANCE.sendToServer(new PacketRailRiders(mc.player.getPosition()));
				this.hotkeyTimeout = true;
			}
		}
	}
}
