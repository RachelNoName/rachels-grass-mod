package net.rachel.rachels_grass.block;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
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
		event.register(ModBlockColors::getGrassTint, ModBlocks.BLOCK_OF_GRASS.get());
	}
	
	private static int getGrassTint(BlockState state, @Nullable BlockAndTintGetter world, @Nullable BlockPos pos, int tintIndex) {		
		if (world == null || pos == null) {
			return -1;
		}
		
		return BiomeColors.getAverageGrassColor(world, pos);
	}
}
