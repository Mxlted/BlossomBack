package dev.codedsakura.blossom.back;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.codedsakura.blossom.lib.BlossomLib;
import dev.codedsakura.blossom.lib.config.BlossomConfig;
import dev.codedsakura.blossom.lib.config.ConfigManager;
import dev.codedsakura.blossom.lib.permissions.Permissions;
import dev.codedsakura.blossom.lib.teleport.TeleportUtils;
import dev.codedsakura.blossom.lib.text.TextUtils;
import dev.codedsakura.blossom.lib.utils.CustomLogger;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.apache.logging.log4j.core.Logger;

import java.util.HashMap;
import java.util.UUID;

import static net.minecraft.server.command.CommandManager.literal;

class BlossomLastDeath {
}

public class BlossomBack implements ModInitializer {
    static BlossomBackConfig CONFIG = ConfigManager.register(BlossomBackConfig.class, "BlossomBack.json", newConfig -> CONFIG = newConfig);
    public static final Logger LOGGER = CustomLogger.createLogger("BlossomBack");
    public static HashMap<UUID, TeleportUtils.TeleportDestination> deaths = new HashMap<>();

    @Override
    public void onInitialize() {
        if (CONFIG.lastDeath == null) {
            LOGGER.trace("updating config from 2.0.3 to ^2.1.0");
            // make new config act as similar as it was previously
            CONFIG.lastDeath = new BlossomBackConfig().lastDeath;
            CONFIG.lastDeath.enabled = true;
            BlossomConfig.save(CONFIG, "BlossomBack.json");
            ConfigManager.refresh(BlossomBackConfig.class);
        }

        BlossomLib.addCommand(literal("back")
                .requires(Permissions.require("blossom.back", true)
                        .and(p -> CONFIG.back.enabled))
                .executes(this::runBack));

        BlossomLib.addCommand(literal("lastdeath")
                .requires(Permissions.require("blossom.last-death", true)
                        .and(p -> CONFIG.lastDeath.enabled))
                .executes(this::runLastDeath));
    }

    private int runBack(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        ServerPlayerEntity player = ctx.getSource().getPlayerOrThrow();

        var destination = TeleportUtils.getLastTeleport(player.getUuid());

        LOGGER.trace("back {} ({}) to {}", player.getEntityName(), player.getUuid(), destination);

        if (destination != null) {
            TeleportUtils.teleport(
                    CONFIG.back.teleportation,
                    CONFIG.back.standStill,
                    CONFIG.back.cooldown,
                    BlossomBack.class,
                    player,
                    () -> destination
            );
        } else {
            TextUtils.sendErr(ctx, "blossom.back.error.no-previous");
        }

        return Command.SINGLE_SUCCESS;
    }

    private int runLastDeath(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        ServerPlayerEntity player = ctx.getSource().getPlayerOrThrow();

        var destination = deaths.get(player.getUuid());

        LOGGER.trace("back (death) {} ({}) to {}", player.getEntityName(), player.getUuid(), destination);

        if (destination != null) {
            TeleportUtils.teleport(
                    CONFIG.lastDeath.teleportation,
                    CONFIG.lastDeath.standStill,
                    CONFIG.lastDeath.cooldown,
                    CONFIG.separateCooldowns ? BlossomLastDeath.class : BlossomBack.class,
                    player,
                    () -> destination
            );
        } else {
            TextUtils.sendErr(ctx, "blossom.back.error.no-death");
        }

        return Command.SINGLE_SUCCESS;
    }
}
