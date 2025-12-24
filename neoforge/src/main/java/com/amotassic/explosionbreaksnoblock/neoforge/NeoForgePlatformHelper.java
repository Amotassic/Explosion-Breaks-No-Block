package com.amotassic.explosionbreaksnoblock.neoforge;

import com.amotassic.explosionbreaksnoblock.platform.IPlatformHelper;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

import java.io.File;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public boolean isModLoaded(String modId) {return ModList.get().isLoaded(modId);}

    @Override
    public File getConfigDirectory() {
        return new File(FMLLoader.getCurrent().getGameDir().toFile(), "config");
    }
}
