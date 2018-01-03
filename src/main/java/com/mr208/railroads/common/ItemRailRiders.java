package com.mr208.railroads.common;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.mr208.railroads.RailRoads;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemRailRiders extends Item
{
	public ItemRailRiders()
	{
		String name = "rail_riders";
		this.setRegistryName(name);
		this.setUnlocalizedName(RailRoads.MOD_ID + "." + name);
		this.setMaxStackSize(1);
		this.setHasSubtypes(false);
		this.setCreativeTab(RailRoads.creativeTab);
		ForgeRegistries.ITEMS.register(this);
		
		RRContent.registeredItems.add(this);
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		if(isInCreativeTab(tab))
			items.add(new ItemStack(this));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		
		tooltip.add(ChatFormatting.DARK_AQUA + I18n.format("item.railroads.rail_riders.tooltip"));
	}
}
