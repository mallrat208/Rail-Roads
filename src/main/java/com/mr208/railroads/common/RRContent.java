package com.mr208.railroads.common;

import com.mr208.railroads.RailRoads;
import com.mr208.railroads.common.entity.EntityRailRiders;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

@Mod.EventBusSubscriber(modid = RailRoads.MOD_ID)
public class RRContent
{
	public static EntityEntry entitySkates;

	public static void onPreInit()
	{
		entitySkates = EntityEntryBuilder.create()
				.entity(EntityRailRiders.class)
				.name("railriders")
				.id(new ResourceLocation(RailRoads.MOD_ID,"rail_riders"), 0)
				.tracker(32,1,true)
				.build();
	}


	@SubscribeEvent
	public static void registerEntityEntries(RegistryEvent.Register<EntityEntry> event)
	{
		event.getRegistry().register(entitySkates);
	}

	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
	{

	}
}
