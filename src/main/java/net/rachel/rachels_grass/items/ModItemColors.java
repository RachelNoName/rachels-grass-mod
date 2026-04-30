package net.rachel.rachels_grass.items;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GrassColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rachel.rachels_grass.RachelsGrass;
import net.rachel.rachels_grass.block.ModBlocks;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = RachelsGrass.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItemColors {
	@SubscribeEvent
	public static void itemColorHandlerEvent(final RegisterColorHandlersEvent.Item event) {
		event.register(ModItemColors::getGrassItemColor, ModBlocks.GRASS_BLOCK.get());
	}
	
	private static int getGrassItemColor(ItemStack item, int tintIndev) {
		return GrassColor.getDefaultColor();
	}
}
