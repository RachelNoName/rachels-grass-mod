package net.rachel.rachels_grass.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.AXIS;

public interface DimensionDryable {
    default void onPlaceDry(BlockState pState, Level pLevel, BlockPos pPos, Block changeInto) {
        if (!pLevel.dimensionType().ultraWarm()) {
            return;
        }

        pLevel.playSound(null, pPos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS,1.0f, 0.8f);
        ((ServerLevel)pLevel).sendParticles(ParticleTypes.CLOUD, pPos.getX() + 0.5d, pPos.getY() + 1.2d, pPos.getZ() + 0.5d, 10, 0.3d, 0.0d, 0.3d, 0.01d);

        pLevel.setBlock(pPos, changeInto.defaultBlockState().setValue(AXIS, pState.getValue(AXIS)), 1);
    }
}
