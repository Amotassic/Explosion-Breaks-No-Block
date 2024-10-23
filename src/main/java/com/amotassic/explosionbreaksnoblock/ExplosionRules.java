package com.amotassic.explosionbreaksnoblock;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class ExplosionRules {
    public static final GameRules.Key<GameRules.BooleanRule> EBNB_ALL =
            GameRuleRegistry.register("EBNB:all", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> EBNB_BED =
            GameRuleRegistry.register("EBNB:bed", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> EBNB_CREEPER =
            GameRuleRegistry.register("EBNB:creeper", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> EBNB_END_CRYSTAL =
            GameRuleRegistry.register("EBNB:end_crystal", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> EBNB_FIREBALL =
            GameRuleRegistry.register("EBNB:fireball", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> EBNB_RESPAWN_ANCHOR =
            GameRuleRegistry.register("EBNB:respawn_anchor", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> EBNB_TNT =
            GameRuleRegistry.register("EBNB:tnt", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> EBNB_TNT_MINECART =
            GameRuleRegistry.register("EBNB:tnt_minecart", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> EBNB_WITHER =
            GameRuleRegistry.register("EBNB:wither", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> EBNB_WITHER_SKULL =
            GameRuleRegistry.register("EBNB:wither_skull", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));

    public static final GameRules.Key<GameRules.BooleanRule> ENID_ALL =
            GameRuleRegistry.register("ENID:all", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> ENID_CREEPER =
            GameRuleRegistry.register("ENID:creeper", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> ENID_END_CRYSTAL =
            GameRuleRegistry.register("ENID:end_crystal", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> ENID_FIREBALL =
            GameRuleRegistry.register("ENID:fireball", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> ENID_RESPAWN_BLOCKS =
            GameRuleRegistry.register("ENID:respawn_blocks", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> ENID_TNT =
            GameRuleRegistry.register("ENID:tnt", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> ENID_TNT_MINECART =
            GameRuleRegistry.register("ENID:tnt_minecart", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> ENID_WITHER =
            GameRuleRegistry.register("ENID:wither", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> ENID_WITHER_SKULL =
            GameRuleRegistry.register("ENID:wither_skull", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));

    public static void ExplosionRulesRegister(){}
}
