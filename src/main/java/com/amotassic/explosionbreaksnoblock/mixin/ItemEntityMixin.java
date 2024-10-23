package com.amotassic.explosionbreaksnoblock.mixin;

import com.amotassic.explosionbreaksnoblock.ExplosionRules;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
    @Shadow public abstract ItemStack getStack();

    public ItemEntityMixin(EntityType<?> type, World world) {super(type, world);}

    @Inject(method = "damage", at = @At(value = "HEAD"), cancellable = true)
    public void damage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {

        boolean a = world.getGameRules().getBoolean(ExplosionRules.ENID_ALL);
        boolean b = world.getGameRules().getBoolean(ExplosionRules.ENID_RESPAWN_BLOCKS);
        boolean c = world.getGameRules().getBoolean(ExplosionRules.ENID_CREEPER);
        boolean e = world.getGameRules().getBoolean(ExplosionRules.ENID_END_CRYSTAL);
        boolean f = world.getGameRules().getBoolean(ExplosionRules.ENID_FIREBALL);
        boolean t = world.getGameRules().getBoolean(ExplosionRules.ENID_TNT);
        boolean tm = world.getGameRules().getBoolean(ExplosionRules.ENID_TNT_MINECART);
        boolean w = world.getGameRules().getBoolean(ExplosionRules.ENID_WITHER);
        boolean ws = world.getGameRules().getBoolean(ExplosionRules.ENID_WITHER_SKULL);

        if (!getStack().isEmpty() && source.isIn(DamageTypeTags.IS_EXPLOSION)) {
            if (a) cir.setReturnValue(false); //总开关

            if (b && Objects.equals(source.getName(), "badRespawnPoint")) cir.setReturnValue(false); //重生方块

            Entity entity = source.getSource();
            if (entity != null) { //其余各类实体造成的爆炸
                if (c && entity instanceof CreeperEntity) cir.setReturnValue(false);
                if (e && entity instanceof EndCrystalEntity) cir.setReturnValue(false);
                if (f && entity instanceof FireballEntity) cir.setReturnValue(false);
                if (t && entity instanceof TntEntity) cir.setReturnValue(false);
                if (tm && entity instanceof TntMinecartEntity) cir.setReturnValue(false);
                if (w && entity instanceof WitherEntity) cir.setReturnValue(false);
                if (ws && entity instanceof WitherSkullEntity) cir.setReturnValue(false);
            }
        }
    }
}
