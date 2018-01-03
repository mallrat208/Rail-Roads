package com.mr208.railroads.common;

import com.mr208.railroads.RailRoads;
import com.mr208.railroads.common.entity.EntityRailRiders;
import com.mr208.railroads.common.recipes.RecipeBootRailRiders;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = RailRoads.MOD_ID)
public class RRContent
{
	public static ArrayList<Item> registeredItems = new ArrayList<>();
	
	public static ArrayList<ItemStack> bootItemStacks = new ArrayList<>();
	
	public static EntityEntry entityRailRiders;
	public static Item itemRailRiders;

	public static void onPreInit()
	{
		itemRailRiders = new ItemRailRiders();
		
		entityRailRiders= EntityEntryBuilder.create()
				.entity(EntityRailRiders.class)
				.name("railriders")
				.id(new ResourceLocation(RailRoads.MOD_ID,"rail_riders"), 0)
				.tracker(32,1,true)
				.build();
	}


	@SubscribeEvent
	public static void registerEntityEntries(RegistryEvent.Register<EntityEntry> event)
	{
		event.getRegistry().register(entityRailRiders);
	}

	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
	{
		ShapedOreRecipe recipe = new ShapedOreRecipe(
				new ResourceLocation(RailRoads.MOD_ID,"rail_riders"),
				itemRailRiders,
				"XX",
				 'X', Items.MINECART
				);
		
		recipe.setRegistryName("rail_riders");
		
		event.getRegistry().register(recipe);
	}
}
