package com.amotassic.explosionbreaksnoblock.mixin;

import com.amotassic.explosionbreaksnoblock.ExplosionRules;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(TntMinecartEntity.class)
public abstract class TntMinecartMixin extends AbstractMinecartEntity {
    protected TntMinecartMixin(EntityType<?> entityType, World world) {super(entityType, world);}

    @ModifyArg(method = "explode(Lnet/minecraft/entity/damage/DamageSource;D)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;createExplosion(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;Lnet/minecraft/world/explosion/ExplosionBehavior;DDDFZLnet/minecraft/world/World$ExplosionSourceType;)V"), index = 8)
    public World.ExplosionSourceType explode(World.ExplosionSourceType type) {
        boolean bl = ((ServerWorld) getWorld()).getGameRules().getBoolean(ExplosionRules.EBNB_TNT_MINECART);
        if (bl) return World.ExplosionSourceType.NONE;
        return type;
    }
}
