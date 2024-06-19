package com.amotassic.explosionbreaksnoblock.mixin;

import com.amotassic.explosionbreaksnoblock.ExplosionRules;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(WitherEntity.class)
public abstract class WithEntityMixin extends HostileEntity {
    protected WithEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {super(entityType, world);}

    @ModifyArg(method = "mobTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFZLnet/minecraft/world/World$ExplosionSourceType;)Lnet/minecraft/world/explosion/Explosion;"), index = 6)
    public World.ExplosionSourceType explode(World.ExplosionSourceType type) {
        boolean bl = this.getWorld().getGameRules().getBoolean(ExplosionRules.EBNB_WITHER);
        if (bl) return World.ExplosionSourceType.NONE;
        return type;
    }
}
