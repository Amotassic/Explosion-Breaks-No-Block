package com.amotassic.explosionbreaksnoblock;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ExplosionBreaksNoBlock implements ModInitializer {

    @Override
    public void onInitialize() {
        Common.init();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> EBNBCommand.register(dispatcher));
    }
}
