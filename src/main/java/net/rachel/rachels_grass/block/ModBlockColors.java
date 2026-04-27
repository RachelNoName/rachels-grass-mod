package net.rachel.rachels_grass.block;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rachel.rachels_grass.RachelsGrass;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = RachelsGrass.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlockColors {
	@SubscribeEvent
	public static void blockColorHandlerEvent(final RegisterColorHandlersEvent.Block event) {
		event.register((state, world, pos, tintIndex) -> {
	           return world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : -1;
	        }, ModBlocks.BLOCK_OF_GRASS.get());
	}
}
