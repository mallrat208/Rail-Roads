package com.mr208.railroads.client;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.mr208.railroads.RailRoads;
import com.mr208.railroads.common.NetworkHandler;
import com.mr208.railroads.common.network.PacketRailRiders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientEvents
{
	public static ClientEvents INSTANCE = new ClientEvents();
	private Minecraft mc = Minecraft.getMinecraft();
	private boolean hotkeyTimeout = false;
	private int hotkeyTimer = 0;

	public ClientEvents()
	{

	}

	public void resetHotkeyTimeout()
	{
		this.hotkeyTimeout = false;
		this.hotkeyTimer = 0;
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onToolTip(ItemTooltipEvent event)
	{
		if(RailRoads.hasSkateTag(event.getItemStack()))
		{
			event.getToolTip().add(ChatFormatting.AQUA + I18n.format("railroads.tooltip.installed.0"));
			event.getToolTip().add(ChatFormatting.DARK_AQUA + I18n.format("railroads.tooltip.installed.1", KeyHandler.skates.getDisplayName()));
		}
	}

	@SubscribeEvent
	public void onKeyInput(ClientTickEvent event)
	{
		if(event.phase==Phase.END)
		{
			if(mc.player!=null&&mc.currentScreen==null&&!mc.player.isRiding())
			{
				if(!this.hotkeyTimeout&&KeyHandler.skates.isPressed())
				{
					NetworkHandler.INSTANCE.sendToServer(new PacketRailRiders(mc.player.getPosition()));
					this.hotkeyTimeout=true;
					this.hotkeyTimer = 50;
					
					return;
				}
			}
			
			//The hotkey gets locked out until the server acknowledges the packet. This timer is on the
			//off chance the server fails to do so. After two and a half seconds, the hotkey gets re-enabled
			
			if(this.hotkeyTimer>0)
				this.hotkeyTimer--;
			
			if(this.hotkeyTimer<0)
			{
				this.hotkeyTimer=0;
				this.hotkeyTimeout=false;
			}
		}
	}
}
