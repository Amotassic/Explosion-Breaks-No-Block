package com.amotassic.explosionbreaksnoblock.mixin;

import com.amotassic.explosionbreaksnoblock.Common;
import com.amotassic.explosionbreaksnoblock.platform.Services;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
    @Shadow public abstract ItemStack getItem();

    public ItemEntityMixin(EntityType<?> type, Level world) {super(type, world);}

    @Inject(method = "hurtServer", at = @At(value = "HEAD"), cancellable = true)
    public void damage(ServerLevel level, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!getItem().isEmpty() && (
                        source.is(DamageTypeTags.IS_EXPLOSION) ||
                                (Services.PLATFORM.isModLoaded("dragonsurvival") && source.is(DamageTypes.WIND_CHARGE))
        )) {
            if (Common.cancelItemDamageByExplosion(source)) cir.setReturnValue(false);
        }
    }
}
