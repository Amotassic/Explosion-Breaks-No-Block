package com.amotassic.explosionbreaksnoblock.mixin;

import com.amotassic.explosionbreaksnoblock.BlockRemoveCache;
import com.amotassic.explosionbreaksnoblock.Common;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Level.class)
public abstract class LevelMixin {

    @Shadow @Final public boolean isClientSide;

    @ModifyArgs(method = "explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;ZLnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/Holder;)Lnet/minecraft/world/level/Explosion;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Explosion;<init>(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Explosion$BlockInteraction;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/Holder;)V"))
    public void CreateExplosion(Args args) {
        Entity entity = args.get(1);
        if (Common.cancel(entity, (Level) (Object) this, args.get(4), args.get(5), args.get(6))) {
            args.set(8, false);
            args.set(9, Explosion.BlockInteraction.KEEP);
        }
    }

    @Inject(method = "removeBlock", at = @At("HEAD"))
    public void removeBlock(BlockPos pos, boolean isMoving, CallbackInfoReturnable<Boolean> cir) {
        if (!isClientSide) BlockRemoveCache.record((Level) (Object) this, pos);
    }

    @Inject(method = "destroyBlock", at = @At("HEAD"))
    public void destroyBlock(BlockPos pos, boolean pDropBlock, Entity pEntity, int pRecursionLeft, CallbackInfoReturnable<Boolean> cir) {
        if (!isClientSide) BlockRemoveCache.record((Level) (Object) this, pos);
    }
}
