package com.amotassic.explosionbreaksnoblock;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import static net.minecraft.commands.Commands.literal;

public class EBNBCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal("explosionbreaksnoblock")
                .requires(source -> source.hasPermission(2))
                .then(literal("reload").executes(EBNBCommand::executeReload))
        );
    }

    static int executeReload(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        try {
            Common.loadConfig();
            source.sendSuccess(() -> Component.translatable("Explosionbreaksnoblock config reloaded!"), true);
            return 1;
        } catch (Exception e) {
            source.sendFailure(Component.translatable("Explosionbreaksnoblock config reload failed!"));
            return 0;
        }
    }

}
