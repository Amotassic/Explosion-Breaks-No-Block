package com.amotassic.explosionbreaksnoblock.mixin;

import com.amotassic.explosionbreaksnoblock.ExplosionRules;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(WitherSkullEntity.class)
public abstract class WitherSkullMixin extends ExplosiveProjectileEntity {
    protected WitherSkullMixin(EntityType<? extends ExplosiveProjectileEntity> entityType, World world) {super(entityType, world);}

    @ModifyArg(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFZLnet/minecraft/world/World$ExplosionSourceType;)Lnet/minecraft/world/explosion/Explosion;"), index = 6)
    public World.ExplosionSourceType explode(World.ExplosionSourceType type) {
        boolean bl = this.getWorld().getGameRules().getBoolean(ExplosionRules.EBNB_WITHER_SKULL);
        if (bl) return World.ExplosionSourceType.NONE;
        return type;
    }
}
