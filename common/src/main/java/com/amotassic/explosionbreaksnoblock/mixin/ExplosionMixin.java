package com.amotassic.explosionbreaksnoblock.mixin;

import com.amotassic.explosionbreaksnoblock.Common;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.ServerExplosion;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerExplosion.class)
public class ExplosionMixin {

    @Mutable @Shadow @Final private boolean fire;
    @Mutable @Shadow @Final private Explosion.BlockInteraction blockInteraction;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(ServerLevel level, Entity entity, DamageSource damageSource, ExplosionDamageCalculator damageCalculator, Vec3 center, float radius, boolean fire, Explosion.BlockInteraction blockInteraction, CallbackInfo ci) {
        if (Common.cancel(entity, level, center)) {
            this.fire = false;
            this.blockInteraction = Explosion.BlockInteraction.KEEP;
        }
    }
}
