package com.mr208.railroads.common.recipes;

import com.google.gson.JsonObject;
import com.mr208.railroads.RailRoads;
import net.minecraft.entity.EntityLiving;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class RecipeBootRailRiders extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe
{
	private final ItemStack riderItem;

	public RecipeBootRailRiders(ItemStack riderItem)
	{
		this.riderItem = riderItem;
	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn)
	{
		boolean boots = false;
		boolean riderUpgradeItem = false;

		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack stack = inv.getStackInSlot(i);
			if (!stack.isEmpty())
			{
				if (EntityLiving.getSlotForItemStack(stack) == EntityEquipmentSlot.FEET && !RailRoads.hasSkateTag(stack) && !boots)
					boots = true;
				else if (stack.isItemEqual(this.riderItem) && !riderUpgradeItem)
					riderUpgradeItem = true;
				else
					return false;
			}
		}
		return boots && riderUpgradeItem;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		ItemStack boots = ItemStack.EMPTY;
		for (int i = 0; i<inv.getSizeInventory();i++)
		{
			ItemStack stack = inv.getStackInSlot(i);
			if(!stack.isEmpty())
				if(EntityLiving.getSlotForItemStack(stack) == EntityEquipmentSlot.FEET)
					boots = stack.copy();
		}

		if(!boots.isEmpty())
		{
			if(boots.hasTagCompound())
			{
				boots.getTagCompound().setBoolean(RailRoads.RAIL_RIDER_TAG,true);
			}
			else
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setBoolean(RailRoads.RAIL_RIDER_TAG, true);
				boots.setTagCompound(tag);
			}

			return boots;
		}

		return ItemStack.EMPTY;
	}

	@Override
	public boolean canFit(int width, int height)
	{
		return width * height >=2;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return ItemStack.EMPTY;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
	{
		return ForgeHooks.defaultRecipeGetRemainingItems(inv);
	}

	@Override
	public boolean isDynamic()
	{
		return true;
	}

	public static class Factory implements IRecipeFactory
	{
		@Override
		public IRecipe parse(JsonContext context, JsonObject object)
		{
			final ItemStack railItem = CraftingHelper.getItemStack(JsonUtils.getJsonObject(object,"railItem"), context);
			return new RecipeBootRailRiders(railItem);
		}
	}
}
