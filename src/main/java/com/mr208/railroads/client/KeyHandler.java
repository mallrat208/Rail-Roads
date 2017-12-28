package com.mr208.railroads.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeyHandler
{
	public static KeyBinding skates;

	public static void init()
	{
		skates = new KeyBinding(
				"railroads.keybinds.riders",
				Keyboard.KEY_C,
				"railroads.keybinds.category");

		ClientRegistry.registerKeyBinding(skates);
	}

}
