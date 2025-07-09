package com.amotassic.explosionbreaksnoblock;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.commands.Commands.literal;

@Mod.EventBusSubscriber(modid = ExplosionBreaksNoBlock.MODID)
public class EBNBCommand {
    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        var dispatcher = event.getDispatcher();
        dispatcher.register(literal("explosionbreaksnoblock")
                .requires(source -> source.hasPermission(2))
                .then(literal("reload").executes(EBNBCommand::executeReload))
        );
    }

    static int executeReload(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        try {
            ExplosionBreaksNoBlock.loadConfig();
            source.sendSuccess(() -> Component.translatable("Explosionbreaksnoblock config reloaded!"), true);
            return 1;
        } catch (Exception e) {
            source.sendFailure(Component.translatable("Explosionbreaksnoblock config reload failed!"));
            return 0;
        }
    }

}
