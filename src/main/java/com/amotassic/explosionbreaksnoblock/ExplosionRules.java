package com.amotassic.explosionbreaksnoblock;

import net.minecraft.world.level.GameRules;

import java.util.HashMap;
import java.util.Map;

public class ExplosionRules {
    public static final Map<String, GameRules.Key<GameRules.BooleanValue>> EBNB_RULES = new HashMap<>();
    public static final Map<String, GameRules.Key<GameRules.BooleanValue>> ENID_RULES = new HashMap<>();

    public static final GameRules.Key<GameRules.BooleanValue>
            EBNB_ALL = ebnbRule("all", "all"),
            EBNB_BED = ebnbRule("bed", "minecraft:bed"),
            EBNB_RESPAWN_ANCHOR = ebnbRule("respawn_anchor", "minecraft:respawn_anchor"),

            ENID_ALL = enidRule("all", "all"),
            ENID_RESPAWN_BLOCKS = enidRule("respawn_blocks", "respawn_blocks");

    private static GameRules.Key<GameRules.BooleanValue> ebnbRule(String name, String key) {
        var rule = GameRules.register("EBNB:" + name, GameRules.Category.MISC, GameRules.BooleanValue.create(false));
        EBNB_RULES.put(key, rule);
        return rule;
    }

    private static GameRules.Key<GameRules.BooleanValue> enidRule(String name, String key) {
        var rule = GameRules.register("ENID:" + name, GameRules.Category.MISC, GameRules.BooleanValue.create(false));
        ENID_RULES.put(key, rule);
        return rule;
    }

    public static void ExplosionRulesRegister() {
        String[] entities = {"creeper", "end_crystal", "fireball", "tnt", "tnt_minecart", "wither", "wither_skull"};
        for (String entity : entities) {
            ebnbRule(entity, "minecraft:" + entity);
            enidRule(entity, "minecraft:" + entity);
        }
    }
}
