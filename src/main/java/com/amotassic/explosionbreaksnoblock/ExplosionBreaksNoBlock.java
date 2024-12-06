package com.amotassic.explosionbreaksnoblock;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.amotassic.explosionbreaksnoblock.ExplosionRules.*;

public class ExplosionBreaksNoBlock implements ModInitializer {
    public static final String MOD_ID = "explosionbreaksnoblock";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        LOGGER.info("Ciallo～(∠·ω< )⌒★");
        ExplosionRules.ExplosionRulesRegister();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> EBNBCommand.register(dispatcher));
    }

    private static final Map<EntityType<?>, GameRules.Key<GameRules.BooleanRule>> EBNB_RULES_MAP = new HashMap<>();
    private static final Map<EntityType<?>, GameRules.Key<GameRules.BooleanRule>> ENID_RULES_MAP = new HashMap<>();

    static {
        EBNB_RULES_MAP.put(EntityType.CREEPER, EBNB_CREEPER);
        EBNB_RULES_MAP.put(EntityType.END_CRYSTAL, EBNB_END_CRYSTAL);
        EBNB_RULES_MAP.put(EntityType.FIREBALL, EBNB_FIREBALL);
        EBNB_RULES_MAP.put(EntityType.TNT, EBNB_TNT);
        EBNB_RULES_MAP.put(EntityType.TNT_MINECART, EBNB_TNT_MINECART);
        EBNB_RULES_MAP.put(EntityType.WITHER, EBNB_WITHER);
        EBNB_RULES_MAP.put(EntityType.WITHER_SKULL, EBNB_WITHER_SKULL);

        ENID_RULES_MAP.put(EntityType.CREEPER, ENID_CREEPER);
        ENID_RULES_MAP.put(EntityType.END_CRYSTAL, ENID_END_CRYSTAL);
        ENID_RULES_MAP.put(EntityType.FIREBALL, ENID_FIREBALL);
        ENID_RULES_MAP.put(EntityType.TNT, ENID_TNT);
        ENID_RULES_MAP.put(EntityType.TNT_MINECART, ENID_TNT_MINECART);
        ENID_RULES_MAP.put(EntityType.WITHER, ENID_WITHER);
        ENID_RULES_MAP.put(EntityType.WITHER_SKULL, ENID_WITHER_SKULL);
    }

    public static boolean EBNB(GameRules rules, Entity entity) {
        if (entity == null) return false;
        var rule = EBNB_RULES_MAP.getOrDefault(entity.getType(), null);
        return rule != null && rules.getBoolean(rule);
    }

    public static boolean ENID(GameRules rules, Entity entity) {
        if (entity == null) return false;
        var rule = ENID_RULES_MAP.getOrDefault(entity.getType(), null);
        return rule != null && rules.getBoolean(rule);
    }

    public static boolean cancel(GameRules rules, Entity entity) {
        if (rules.getBoolean(EBNB_ALL)) return true;
        return EBNB(rules, entity);
    }
    
    public static boolean cancelItemDamageByExplosion(GameRules rules, DamageSource source) {
        if (rules.getBoolean(ENID_ALL)) return true;
        if (rules.getBoolean(ENID_RESPAWN_BLOCKS) && Objects.equals(source.getName(), "badRespawnPoint")) return true;
        return ENID(rules, source.getSource());
    }
}
