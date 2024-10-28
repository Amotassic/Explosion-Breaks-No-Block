package com.amotassic.explosionbreaksnoblock;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExplosionBreaksNoBlock implements ModInitializer {
    public static final String MOD_ID = "explosionbreaksnoblock";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        LOGGER.info("Ciallo～(∠·ω< )⌒★");
        ExplosionRules.ExplosionRulesRegister();
    }

    public static boolean cancel(GameRules rules, Entity entity) {
        boolean all = rules.getBoolean(ExplosionRules.EBNB_ALL);
        boolean c = rules.getBoolean(ExplosionRules.EBNB_CREEPER);
        boolean e = rules.getBoolean(ExplosionRules.EBNB_END_CRYSTAL);
        boolean f = rules.getBoolean(ExplosionRules.EBNB_FIREBALL);
        boolean t = rules.getBoolean(ExplosionRules.EBNB_TNT);
        boolean tm = rules.getBoolean(ExplosionRules.EBNB_TNT_MINECART);
        boolean w = rules.getBoolean(ExplosionRules.EBNB_WITHER);
        boolean ws = rules.getBoolean(ExplosionRules.EBNB_WITHER_SKULL);

        if (all) return true;
        return switch (entity) {
            case CreeperEntity      ignored when c -> true;
            case EndCrystalEntity   ignored when e -> true;
            case FireballEntity     ignored when f -> true;
            case TntEntity          ignored when t -> true;
            case TntMinecartEntity  ignored when tm -> true;
            case WitherEntity       ignored when w -> true;
            case WitherSkullEntity  ignored when ws -> true;
            case null, default -> false;
        };
    }
}
