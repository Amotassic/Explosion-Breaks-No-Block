package com.amotassic.explosionbreaksnoblock.platform;

import com.amotassic.explosionbreaksnoblock.ExplosionRules;
import net.minecraft.world.level.GameRules;
import net.neoforged.fml.ModList;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override public String getPlatformName() {return "NeoForge";}

    @Override
    public boolean isModLoaded(String modId) {return ModList.get().isLoaded(modId);}

    @Override
    public GameRules.Key<GameRules.BooleanValue> ebnbRule(String name, String key) {
        var rule = GameRules.register("EBNB:" + name, GameRules.Category.MISC, GameRules.BooleanValue.create(false));
        ExplosionRules.EBNB_RULES.put(key, rule);
        return rule;
    }

    @Override
    public GameRules.Key<GameRules.BooleanValue> enidRule(String name, String key) {
        var rule = GameRules.register("ENID:" + name, GameRules.Category.MISC, GameRules.BooleanValue.create(false));
        ExplosionRules.ENID_RULES.put(key, rule);
        return rule;
    }
}
