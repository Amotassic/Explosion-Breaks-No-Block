package com.amotassic.explosionbreaksnoblock;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.world.GameRules.*;

public class EBNBCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("explosionbreaksnoblock")
                .requires(source -> source.hasPermissionLevel(2))
                        .executes(EBNBCommand::executeQuery)
                .then(literal("resetAll").executes(EBNBCommand::executeReset))
        );
    }

    static int executeQuery(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        MinecraftServer server = source.getServer();
        int on = 0;
        for (var key : getRules()) {
            //noinspection unchecked
            Key<BooleanRule> ruleKey = (Key<BooleanRule>) key;
            if (server.getGameRules().getBoolean(ruleKey)) {
                var rule = server.getGameRules().get(key);
                source.sendFeedback(() -> Text.translatable("commands.gamerule.query", key.getName(), rule.toString()), false);
                on++;
            }
        }
        if (on == 0) source.sendFeedback(() -> Text.translatable("ebnb.not_enable"), false);
        return on;
    }

    static int executeReset(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        MinecraftServer server = source.getServer();
        for (Key<?> key : getRules()) {
            //noinspection unchecked
            Key<BooleanRule> ruleKey = (Key<BooleanRule>) key;
            server.getGameRules().get(ruleKey).set(false, server);
        }
        source.sendFeedback(() -> Text.translatable("ebnb.reset"), true);
        return 1;
    }

    private static List<Key<?>> getRules() {
        List<Key<?>> keys = new ArrayList<>();
        Class<?> clazz = ExplosionRules.class;
        for (Field field : clazz.getFields()) {
            if (field.getType().equals(Key.class)) {
                try {
                    keys.add((Key<?>) field.get(null));
                } catch (IllegalAccessException e) {
                    //noinspection CallToPrintStackTrace
                    e.printStackTrace();
                }
            }
        }
        return keys;
    }
}
