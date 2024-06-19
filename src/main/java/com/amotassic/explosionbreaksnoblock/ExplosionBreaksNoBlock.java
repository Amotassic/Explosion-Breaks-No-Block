package com.amotassic.explosionbreaksnoblock;

import net.fabricmc.api.ModInitializer;
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
}
