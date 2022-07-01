package fabric_mods.dragon_eggs_plus.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.EndPortalFeature;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderDragonFight.class)
public class EnderDragonFightMixin {
	@Inject(at = @At("TAIL"), method = "Lnet/minecraft/entity/boss/dragon/EnderDragonFight;dragonKilled(Lnet/minecraft/entity/boss/dragon/EnderDragonEntity;)V")
	private void dragonKilled(EnderDragonEntity dragonEntity, CallbackInfo ci) {
		World world = dragonEntity.getWorld();
		BlockPos egglocation = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, EndPortalFeature.ORIGIN);
		BlockState state = world.getBlockState(egglocation.offset(Direction.DOWN));
		if (state == null || !state.isOf(Blocks.DRAGON_EGG)) {
			world.setBlockState(egglocation, Blocks.DRAGON_EGG.getDefaultState());
		}
	}
}