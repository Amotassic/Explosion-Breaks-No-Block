package com.amotassic.explosionbreaksnoblock.fabric;

import com.amotassic.explosionbreaksnoblock.BlockRemoveCache;
import com.amotassic.explosionbreaksnoblock.Common;
import com.amotassic.explosionbreaksnoblock.EBNBCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class ExplosionBreaksNoBlock implements ModInitializer {

    @Override
    public void onInitialize() {
        Common.init();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> EBNBCommand.register(dispatcher));
        ServerTickEvents.END_WORLD_TICK.register(BlockRemoveCache::onTick);
    }
}
