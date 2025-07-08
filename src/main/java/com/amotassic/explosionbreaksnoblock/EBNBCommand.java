package com.amotassic.explosionbreaksnoblock;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.commands.Commands.literal;

@Mod.EventBusSubscriber(modid = ExplosionBreaksNoBlock.MODID)
public class EBNBCommand {
    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        var dispatcher = event.getDispatcher();
        dispatcher.register(literal("explosionbreaksnoblock")
                .requires(source -> source.hasPermission(2))
                        .executes(EBNBCommand::executeQuery)
                .then(literal("resetAll").executes(EBNBCommand::executeReset))
        );
    }

    static int executeQuery(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        MinecraftServer server = source.getServer();
        int on = 0;
        for (var key : getRules()) {
            if (server.getGameRules().getBoolean(key)) {
                var rule = server.getGameRules().getRule(key);
                source.sendSuccess(() -> Component.translatable("commands.gamerule.query", key.getId(), rule.toString()), false);
                on++;
            }
        }
        if (on == 0) source.sendSuccess(() -> Component.translatable("ebnb.not_enable"), false);
        return on;
    }

    static int executeReset(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        MinecraftServer server = source.getServer();
        for (GameRules.Key<GameRules.BooleanValue> key : getRules()) {
            server.getGameRules().getRule(key).set(false, server);
        }
        source.sendSuccess(() -> Component.translatable("ebnb.reset"), true);
        return 1;
    }

    private static List<GameRules.Key<GameRules.BooleanValue>> getRules() {
        List<GameRules.Key<GameRules.BooleanValue>> keys = new ArrayList<>(ExplosionRules.EBNB_RULES.values());
        keys.addAll(ExplosionRules.ENID_RULES.values());
        return keys;
    }
}
