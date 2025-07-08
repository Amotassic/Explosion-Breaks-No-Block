package com.amotassic.explosionbreaksnoblock;

import com.amotassic.explosionbreaksnoblock.platform.Services;
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

    private static GameRules.Key<GameRules.BooleanValue> ebnbRule(String name, String id) {
        return Services.PLATFORM.ebnbRule(name, id);
    }

    private static GameRules.Key<GameRules.BooleanValue> enidRule(String name, String key) {
        return Services.PLATFORM.enidRule(name, key);
    }

    public static void ExplosionRulesRegister() {
        String[] entities = {"creeper", "end_crystal", "fireball", "tnt", "tnt_minecart", "wither", "wither_skull"};
        for (String entity : entities) {
            ebnbRule(entity, "minecraft:" + entity);
            enidRule(entity, "minecraft:" + entity);
        }
    }
}
