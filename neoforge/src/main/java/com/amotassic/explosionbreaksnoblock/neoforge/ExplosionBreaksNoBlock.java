package com.amotassic.explosionbreaksnoblock.neoforge;

import com.amotassic.explosionbreaksnoblock.BlockRemoveCache;
import com.amotassic.explosionbreaksnoblock.Common;
import com.amotassic.explosionbreaksnoblock.EBNBCommand;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@Mod(Common.MOD_ID)
public class ExplosionBreaksNoBlock {

    public ExplosionBreaksNoBlock(IEventBus eventBus) {
        Common.init();
        NeoForge.EVENT_BUS.addListener(RegisterCommandsEvent.class, evt -> EBNBCommand.register(evt.getDispatcher()));
        NeoForge.EVENT_BUS.addListener(this::onTick);
    }

    private void onTick(LevelTickEvent.Post event) {
        BlockRemoveCache.onTick(event.getLevel());
    }
}
