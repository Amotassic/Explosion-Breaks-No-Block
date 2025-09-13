package com.amotassic.explosionbreaksnoblock.mixin;

import com.amotassic.explosionbreaksnoblock.BlockRemoveCache;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
