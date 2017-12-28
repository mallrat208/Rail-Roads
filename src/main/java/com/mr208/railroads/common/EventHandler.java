package com.mr208.railroads.common;

import com.mr208.railroads.RailRoads;
import com.mr208.railroads.common.entity.EntityRailRiders;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler
{
	public static final EventHandler INSTANCE = new EventHandler();

	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();

		if(!entity.getEntityWorld().isRemote)
		{
			if(entity.isRiding() && entity.getRidingEntity() instanceof EntityRailRiders)
			{
				ItemStack footSlot = entity.getItemStackFromSlot(EntityEquipmentSlot.FEET);

				//Ensure the Player Still has on Boots and that they have the appropriate tag
				if(footSlot.isEmpty() || !RailRoads.hasSkateTag(footSlot))
				{
					entity.dismountRidingEntity();
				}
				//If the config is enabled, damage the boots as they're used on the Rails
				else if(ConfigHandler.RAILS_DAMAGE_BOOTS && entity.ticksExisted % 10 == 0)
				{
					footSlot.damageItem(1, entity);
				}
			}
		}
	}
}
