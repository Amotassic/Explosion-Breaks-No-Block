package com.amotassic.explosionbreaksnoblock.mixin;

import com.amotassic.explosionbreaksnoblock.Common;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ServerLevel.class)
public abstract class CreateExplosionMixin {
    @ModifyArgs(method = "explode", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/ServerExplosion;<init>(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;Lnet/minecraft/world/phys/Vec3;FZLnet/minecraft/world/level/Explosion$BlockInteraction;)V"))
    public void CreateExplosion(Args args) {
        Entity entity = args.get(1);
        if (Common.cancel(entity)) {
            args.set(6, false);
            args.set(7, Explosion.BlockInteraction.KEEP);
        }
    }
}
