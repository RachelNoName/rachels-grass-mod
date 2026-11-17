package net.rachel.rachels_grass.block.custom;


import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.rachel.rachels_grass.block.ModBlocks;


public class GrassBlock extends GrassBlockBase implements DimensionDryable, SunDryable {
	public GrassBlock(Properties pProperties) {
		super(pProperties);
	}
	
	@Override
	public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
		this.onPlaceDry(pState, pLevel, pPos, ModBlocks.DRY_GRASS_BLOCK.get());
	}

	@Override
	public boolean isRandomlyTicking(BlockState pState) {
		return true;
	}

	@Override
	public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
		this.tick(pState, pLevel, pPos, pRandom);
		this.onRandomTick(pState, pLevel, pPos, pRandom);
	}
}
