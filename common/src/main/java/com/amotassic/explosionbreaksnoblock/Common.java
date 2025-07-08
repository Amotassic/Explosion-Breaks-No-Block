package com.amotassic.explosionbreaksnoblock;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

import static com.amotassic.explosionbreaksnoblock.ExplosionRules.*;

public class Common {

    public static final String MOD_ID = "explosionbreaksnoblock";
    public static final String MOD_NAME = "Explosion Breaks No Block";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {
        LOGGER.info("Ciallo～(∠·ω< )⌒★");
        ExplosionRules.ExplosionRulesRegister();
    }

    public static boolean cancel(GameRules rules, Entity entity) {
        if (rules.getBoolean(EBNB_ALL)) return true;

        if (entity == null) return false;
        var rule = EBNB_RULES.getOrDefault(BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString(), null);
        return rule != null && rules.getBoolean(rule);
    }

    public static boolean cancelItemDamageByExplosion(GameRules rules, DamageSource source) {
        if (rules.getBoolean(ENID_ALL)) return true;
        if (rules.getBoolean(ENID_RESPAWN_BLOCKS) && Objects.equals(source.getMsgId(), "badRespawnPoint")) return true;
        Entity entity = source.getEntity();

        if (entity == null) return false;
        var rule = ENID_RULES.getOrDefault(BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString(), null);
        return rule != null && rules.getBoolean(rule);
    }
}
