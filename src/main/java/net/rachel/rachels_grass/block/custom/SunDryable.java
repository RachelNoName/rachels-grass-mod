package net.rachel.rachels_grass.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockState;
import net.rachel.rachels_grass.RachelsGrass;

public interface SunDryable {
    default void onRandomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.isRaining()) {
            attemptWet(pLevel, pPos, pRandom);
        }
        else {
            attemptDry(pLevel, pPos, pRandom);
        }
    }

    private void attemptDry(ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.isNight()) {
            return;
        }

        int skyLight = getHighestSurroundingLight(pLevel, pPos);

        if (skyLight < 6) {
            return;
        }

        float dryingChance = (float) (skyLight - 5) / 100;

        if (pRandom.nextFloat() > dryingChance) {
            RachelsGrass.LOGGER.info("Grass block dried");
        }
    }

    private void attemptWet(ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.isRainingAt(pPos.above())) {
            if (pRandom.nextFloat() < 0.1f) {
                RachelsGrass.LOGGER.info("Grass block got wet");
            }
        } else if (pLevel.isRainingAt(pPos.north()) || pLevel.isRainingAt(pPos.east()) || pLevel.isRainingAt(pPos.south()) || pLevel.isRainingAt(pPos.west())) {
            if (pRandom.nextFloat() < 0.05f) {
                RachelsGrass.LOGGER.info("Grass block got wet (From side rain)");
            }
        }
    }

    private int getHighestSurroundingLight(ServerLevel pLevel, BlockPos pPos) {
        int[] lightLevels = {
                pLevel.getBrightness(LightLayer.SKY, pPos.above()),
                pLevel.getBrightness(LightLayer.SKY, pPos.below()),
                pLevel.getBrightness(LightLayer.SKY, pPos.north()),
                pLevel.getBrightness(LightLayer.SKY, pPos.west()),
                pLevel.getBrightness(LightLayer.SKY, pPos.south()),
                pLevel.getBrightness(LightLayer.SKY, pPos.east()),
        };

        int skyLight = 0;

        for (int i = 0; i < lightLevels.length; i++) {
            if (lightLevels[i] > skyLight) {
                skyLight = lightLevels[i];
            }
        }

        return skyLight;
    }
}
