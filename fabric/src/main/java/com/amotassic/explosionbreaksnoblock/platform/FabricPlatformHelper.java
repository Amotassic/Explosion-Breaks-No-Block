package com.amotassic.explosionbreaksnoblock.platform;

import net.fabricmc.loader.api.FabricLoader;

import java.io.File;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override public String getPlatformName() {return "Fabric";}

    @Override
    public boolean isModLoaded(String modId) {return FabricLoader.getInstance().isModLoaded(modId);}

    @Override
    public File getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir().toFile();
    }
}
