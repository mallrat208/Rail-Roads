package com.mr208.railroads;

import com.mr208.railroads.common.CommonProxy;
import com.mr208.railroads.common.RRContent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

@Mod(modid = RailRoads.MOD_ID, name = RailRoads.MOD_NAME, version = RailRoads.MOD_VERS)
public class RailRoads
{
	public static final String MOD_ID = "railroads";
	public static final String MOD_NAME = "Rail Roads";
	public static final String MOD_VERS = "1.1.1";

	public static final String RAIL_RIDER_TAG = "RailRiders";

	private static final String COMMON_PROXY = "com.mr208.railroads.common.CommonProxy";
	private static final String CLIENT_PROXY = "com.mr208.railroads.client.ClientProxy";
	
	public static Logger LOGGER;
	
	@SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		LOGGER = event.getModLog();
		
		RailRoads.proxy.onPreInit();
	}

	@Mod.EventHandler
	public void onInit(FMLInitializationEvent event)
	{
		RailRoads.proxy.onInit();
	}
	
	@Mod.EventHandler
	public void onPostInit(FMLPostInitializationEvent event)
	{
		RailRoads.LOGGER.info("Searching for Boots");
		Iterator it = Item.REGISTRY.iterator();
		
		while(it.hasNext())
		{
			Item item = (Item)it.next();
			
			if(EntityLiving.getSlotForItemStack(new ItemStack(item)) == EntityEquipmentSlot.FEET)
			{
				RRContent.bootItemStacks.add(new ItemStack(item));
			}
		}
		
		RailRoads.LOGGER.info("Found {} sets of Boots", RRContent.bootItemStacks.size());
	}

	public static CreativeTabs creativeTab = new CreativeTabs(RailRoads.MOD_ID)
	{
		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(RRContent.itemRailRiders);
		}
		
		@Override
		public void displayAllRelevantItems(NonNullList<ItemStack> p_78018_1_)
		{
			super.displayAllRelevantItems(p_78018_1_);
			
			NBTTagCompound tagCompound = new NBTTagCompound();
			tagCompound.setBoolean(RAIL_RIDER_TAG, true);
			
			for(ItemStack boots: RRContent.bootItemStacks)
			{
				boots.setTagCompound(tagCompound);
				p_78018_1_.add(boots);
			}
		}
	};
	
	public static boolean hasSkateTag(ItemStack boots)
	{
		if(boots.hasTagCompound() && boots.getTagCompound().hasKey(RAIL_RIDER_TAG))
		{
			return boots.getTagCompound().getBoolean(RAIL_RIDER_TAG);
		}

		return false;
	}
}
