package com.amotassic.explosionbreaksnoblock.mixin;

import com.amotassic.explosionbreaksnoblock.BlockRemoveCache;
import com.amotassic.explosionbreaksnoblock.Common;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ServerLevel.class)
abstract class ServerLevelMixin {
    @ModifyArgs(method = "explode", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/ServerExplosion;<init>(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;Lnet/minecraft/world/phys/Vec3;FZLnet/minecraft/world/level/Explosion$BlockInteraction;)V"))
    public void CreateExplosion(Args args) {
        Entity entity = args.get(1); Vec3 vec3 = args.get(4);
        if (Common.cancel(entity, (Level) (Object) this, vec3)) {
            args.set(6, false);
            args.set(7, Explosion.BlockInteraction.KEEP);
        }
    }
}

@Mixin(Level.class)
public abstract class LevelMixin {

    @Shadow @Final public boolean isClientSide;

    @Inject(method = "removeBlock", at = @At("HEAD"))
    public void removeBlock(BlockPos pos, boolean isMoving, CallbackInfoReturnable<Boolean> cir) {
        if (!isClientSide) BlockRemoveCache.record((Level) (Object) this, pos);
    }

    @Inject(method = "destroyBlock", at = @At("HEAD"))
    public void destroyBlock(BlockPos pos, boolean pDropBlock, Entity pEntity, int pRecursionLeft, CallbackInfoReturnable<Boolean> cir) {
        if (!isClientSide) BlockRemoveCache.record((Level) (Object) this, pos);
    }
}
