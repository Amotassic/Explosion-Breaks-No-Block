package com.amotassic.explosionbreaksnoblock.mixin;

import com.amotassic.explosionbreaksnoblock.ExplosionRules;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BedBlock.class)
public abstract class BedBlockMixin {
    @Inject(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;toCenterPos()Lnet/minecraft/util/math/Vec3d;"), cancellable = true)
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        boolean bed = ((ServerWorld) world).getGameRules().getBoolean(ExplosionRules.EBNB_BED);
        if (bed) {
            Vec3d vec3d = pos.toCenterPos();
            world.createExplosion(null, world.getDamageSources().badRespawnPoint(vec3d), null, vec3d, 5.0F, false, World.ExplosionSourceType.NONE);
            cir.setReturnValue(ActionResult.SUCCESS_SERVER);
        }
    }
}
