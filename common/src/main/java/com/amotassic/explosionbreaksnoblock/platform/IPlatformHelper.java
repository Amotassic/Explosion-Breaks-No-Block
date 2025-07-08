package com.amotassic.explosionbreaksnoblock.platform;

import net.minecraft.world.level.GameRules;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    GameRules.Key<GameRules.BooleanValue> ebnbRule(String name, String key);
    GameRules.Key<GameRules.BooleanValue> enidRule(String name, String key);
}
