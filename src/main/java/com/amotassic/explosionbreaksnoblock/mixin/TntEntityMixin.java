package com.amotassic.explosionbreaksnoblock.mixin;

import com.amotassic.explosionbreaksnoblock.ExplosionRules;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(TntEntity.class)
public abstract class TntEntityMixin extends Entity {
    public TntEntityMixin(EntityType<?> type, World world) {super(type, world);}

    @ModifyArg(method = "explode", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/World$ExplosionSourceType;)Lnet/minecraft/world/explosion/Explosion;"), index = 5)
    public World.ExplosionSourceType explode(World.ExplosionSourceType type) {
        boolean bl = this.getWorld().getGameRules().getBoolean(ExplosionRules.EBNB_TNT);
        if (bl) return World.ExplosionSourceType.NONE;
        return type;
    }
}
