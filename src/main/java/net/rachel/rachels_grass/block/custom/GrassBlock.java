package net.rachel.rachels_grass.block.custom;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.extensions.IForgeBlock;


public class GrassBlock extends RotatedPillarBlock implements IForgeBlock {	
	public static final int FLAMABILITY = 60;

	public GrassBlock(Properties pProperties) {
		super(pProperties);
		// TODO Auto-generated constructor stub
	}
	
	public void fallOn(Level pLevel, BlockState pBlockState, BlockPos pBlockPos, Entity pEntity, float pFallDistance) {
		pEntity.causeFallDamage(pFallDistance, 0.2F, pLevel.damageSources().fall());
	}
	
	
	@Override
	public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
		if (!pLevel.dimensionType().ultraWarm()) {
			return;
		}
		
		pLevel.playSound(null, pPos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS,1.0f, 0.8f);
		((ServerLevel)pLevel).sendParticles(ParticleTypes.CLOUD, pPos.getX() + 0.5d, pPos.getY() + 1.2d, pPos.getZ() + 0.5d, 10, 0.3d, 0.0d, 0.3d, 0.01d);
		
		// TODO: Replace the HAY_BLOCK with the actual dry grass block later
		pLevel.setBlock(pPos, Blocks.HAY_BLOCK.defaultBlockState().setValue(AXIS, pState.getValue(AXIS)), 1);
	}
	
	@Override
	public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		return FLAMABILITY;
	}
}
