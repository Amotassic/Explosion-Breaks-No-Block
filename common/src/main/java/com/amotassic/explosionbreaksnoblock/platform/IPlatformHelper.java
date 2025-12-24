package com.amotassic.explosionbreaksnoblock.platform;

import java.io.File;

public interface IPlatformHelper {

    boolean isModLoaded(String modId);

    File getConfigDirectory();
}
