package net.rachel.rachels_grass.items;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rachel.rachels_grass.RachelsGrass;
import net.rachel.rachels_grass.block.ModBlockColors;
import net.rachel.rachels_grass.block.ModBlocks;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = RachelsGrass.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItemColors {
	private static final int GRASS_YELLOW = 0xd1b056;

	@SubscribeEvent
	public static void itemColorHandlerEvent(final RegisterColorHandlersEvent.Item event) {
		event.register(ModItemColors::getGrassItemColor, ModBlocks.GRASS_BLOCK.get());
		event.register(ModItemColors::getSlightDryGrassItemColor, ModBlocks.SLIGHTLY_DRY_GRASS_BLOCK.get());
		event.register(ModItemColors::getVeryDryGrassItemColor, ModBlocks.VERY_DRY_GRASS_BLOCK.get());
	}


	private static int getGrassItemColor(ItemStack item, int tintIndex) {
		return GrassColor.getDefaultColor();
	}

	private static int getSlightDryGrassItemColor(ItemStack item, int tintIndex) {
		int grassColor = GrassColor.getDefaultColor();

		return ModItemColors.colorLerp(grassColor, GRASS_YELLOW, 0.4f);
	}


	private static int getVeryDryGrassItemColor(ItemStack item, int tintIndex) {
		int grassColor = GrassColor.getDefaultColor();

		return ModItemColors.colorLerp(grassColor, GRASS_YELLOW, 0.8f);
	}


	private static int colorLerp(int from, int to, float t) {
		int fromR = (from & (255 << 16)) >> 16;
		int fromG = (from & (255 << 8)) >> 8;
		int fromB = (from & 255);

		int toR = (to & (255 << 16)) >> 16;
		int toG = (to & (255 << 8)) >> 8;
		int toB = (to & 255);

		int resultR = (int) ((float) fromR * (1.0f - t) + ((float) toR * t));
		int resultG = (int) ((float) fromG * (1.0f - t) + ((float) toG * t));
		int resultB = (int) ((float) fromB * (1.0f - t) + ((float) toB * t));

		return (resultR << 16) | (resultG << 8) | resultB;
	}
}
