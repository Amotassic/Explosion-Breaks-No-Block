package com.amotassic.explosionbreaksnoblock.mixin;

import com.amotassic.explosionbreaksnoblock.ExplosionBreaksNoBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BedBlock.class)
public abstract class BedBlockMixin {
    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;getCenter()Lnet/minecraft/world/phys/Vec3;"), cancellable = true)
    public void onUse(BlockState p_49515_, Level world, BlockPos pos, Player p_49518_, InteractionHand p_49519_, BlockHitResult p_49520_, CallbackInfoReturnable<InteractionResult> cir) {
        boolean bed = ExplosionBreaksNoBlock.EBNBList.contains("minecraft:bed");
        if (bed) {
            Vec3 vec3d = pos.getCenter();
            world.explode(null, world.damageSources().badRespawnPointExplosion(vec3d), null, vec3d, 5.0F, false, Level.ExplosionInteraction.NONE);
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}
