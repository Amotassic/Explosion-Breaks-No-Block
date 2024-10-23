package com.amotassic.explosionbreaksnoblock.mixin;

import com.amotassic.explosionbreaksnoblock.ExplosionRules;
import net.minecraft.block.BlockState;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RespawnAnchorBlock.class)
public abstract class RespawnAnchorMixin {
    @Inject(method = "explode", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;toCenterPos()Lnet/minecraft/util/math/Vec3d;"), cancellable = true)
    private void explode(BlockState state, World world, BlockPos explodedPos, CallbackInfo ci) {
        boolean bl = ((ServerWorld) world).getGameRules().getBoolean(ExplosionRules.EBNB_RESPAWN_ANCHOR);
        if (bl) {
            Vec3d vec3d = explodedPos.toCenterPos();
            world.createExplosion(null, world.getDamageSources().badRespawnPoint(vec3d), null, vec3d, 5.0f, false, World.ExplosionSourceType.NONE);
            ci.cancel();
        }
    }
}
