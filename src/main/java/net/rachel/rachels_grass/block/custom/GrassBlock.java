package net.rachel.rachels_grass.block.custom;


import javax.annotation.Nullable;

import org.joml.Vector3f;

import net.minecraft.client.particle.DustParticle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.PlayerCloudParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipBlockStateContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
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
