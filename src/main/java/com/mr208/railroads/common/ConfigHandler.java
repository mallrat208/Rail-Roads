package com.mr208.railroads.common;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.Comment;
import com.mr208.railroads.RailRoads;

@Config(modid = RailRoads.MOD_ID, name = RailRoads.MOD_NAME)
public class ConfigHandler
{
	@Comment("By default, boots will wear down when used as Rail Skates.")
	public static boolean RAILS_DAMAGE_BOOTS = true;
}
