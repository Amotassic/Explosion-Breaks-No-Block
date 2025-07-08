package com.amotassic.explosionbreaksnoblock;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.util.Objects;

import static com.amotassic.explosionbreaksnoblock.ExplosionRules.*;

@Mod(ExplosionBreaksNoBlock.MODID)
public class ExplosionBreaksNoBlock {
    public static final String MODID = "explosionbreaksnoblock";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ExplosionBreaksNoBlock() {
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
