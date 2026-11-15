package net.rachel.rachels_grass.block.custom;


import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockState;
import net.rachel.rachels_grass.RachelsGrass;
import net.rachel.rachels_grass.block.ModBlocks;


public class GrassBlock extends GrassBlockBase{
	public GrassBlock(Properties pProperties) {
		super(pProperties);
	}
	
	@Override
	public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
		if (!pLevel.dimensionType().ultraWarm()) {
			return;
		}
		
		pLevel.playSound(null, pPos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS,1.0f, 0.8f);
		((ServerLevel)pLevel).sendParticles(ParticleTypes.CLOUD, pPos.getX() + 0.5d, pPos.getY() + 1.2d, pPos.getZ() + 0.5d, 10, 0.3d, 0.0d, 0.3d, 0.01d);
		
		pLevel.setBlock(pPos, ModBlocks.DRY_GRASS_BLOCK.get().defaultBlockState().setValue(AXIS, pState.getValue(AXIS)), 1);
	}

	@Override
	public boolean isRandomlyTicking(BlockState pState) {
		return true;
	}

	@Override
	public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
		this.tick(pState, pLevel, pPos, pRandom);

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
