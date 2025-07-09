package com.amotassic.explosionbreaksnoblock.mixin;

import com.amotassic.explosionbreaksnoblock.ExplosionBreaksNoBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RespawnAnchorBlock.class)
public abstract class RespawnAnchorMixin {
    @Inject(method = "explode", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;getCenter()Lnet/minecraft/world/phys/Vec3;"), cancellable = true)
    private void explode(BlockState state, Level world, BlockPos explodedPos, CallbackInfo ci) {
        boolean bl = ExplosionBreaksNoBlock.EBNBList.contains("minecraft:respawn_anchor");
        if (bl) {
            Vec3 vec3d = explodedPos.getCenter();
            world.explode(null, world.damageSources().badRespawnPointExplosion(vec3d), null, vec3d, 5.0f, false, Level.ExplosionInteraction.NONE);
            ci.cancel();
        }
    }
}
