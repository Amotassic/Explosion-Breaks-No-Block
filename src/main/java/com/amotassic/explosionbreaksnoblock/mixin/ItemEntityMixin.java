package com.amotassic.explosionbreaksnoblock.mixin;

import com.amotassic.explosionbreaksnoblock.ExplosionBreaksNoBlock;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
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

    @Inject(method = "hurt", at = @At(value = "HEAD"), cancellable = true)
    public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!level().isClientSide && !getItem().isEmpty() && (
                source.is(DamageTypeTags.IS_EXPLOSION)
        )) {
            if (ExplosionBreaksNoBlock.cancelItemDamageByExplosion(source, level())) cir.setReturnValue(false);
        }
    }
}
