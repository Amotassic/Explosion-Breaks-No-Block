package com.amotassic.explosionbreaksnoblock.platform;

import com.amotassic.explosionbreaksnoblock.ExplosionRules;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.level.GameRules;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override public String getPlatformName() {return "Fabric";}

    @Override
    public boolean isModLoaded(String modId) {return FabricLoader.getInstance().isModLoaded(modId);}

    @Override
    public GameRules.Key<GameRules.BooleanValue> ebnbRule(String name, String key) {
        var rule = GameRuleRegistry.register("EBNB:" + name, GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
        ExplosionRules.EBNB_RULES.put(key, rule);
        return rule;
    }

    @Override
    public GameRules.Key<GameRules.BooleanValue> enidRule(String name, String key) {
        var rule = GameRuleRegistry.register("ENID:" + name, GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
        ExplosionRules.ENID_RULES.put(key, rule);
        return rule;
    }
}
