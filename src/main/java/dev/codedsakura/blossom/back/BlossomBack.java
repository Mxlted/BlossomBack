package dev.codedsakura.blossom.back;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.codedsakura.blossom.lib.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.apache.logging.log4j.core.Logger;

import static net.minecraft.server.command.CommandManager.literal;

public class BlossomBack implements ModInitializer {
    static BlossomBackConfig CONFIG = ConfigManager.register(BlossomBackConfig.class, "BlossomBack.json", newConfig -> CONFIG = newConfig);
    public static final Logger LOGGER = CustomLogger.createLogger("BlossomBack");

    @Override
    public void onInitialize() {
        BlossomLib.addCommand(literal("back")
            .requires(Permissions.require("blossom.back", true))
            .executes(this::runBack));
    }

    private int runBack(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        ServerPlayerEntity player = ctx.getSource().getPlayer();
        var destination = TeleportUtils.getLastTeleport(player.getUuid());

        LOGGER.trace("back {} to {}", player, destination);

        if (destination != null) {
            TeleportUtils.teleport(
                CONFIG.teleportation,
                CONFIG.standStill,
                CONFIG.cooldown,
                BlossomBack.class,
                player,
                () -> destination
            );
        } else {
            TextUtils.sendErr(ctx, "blossom.back.error.no-previous");
        }

        return Command.SINGLE_SUCCESS;
    }
}
