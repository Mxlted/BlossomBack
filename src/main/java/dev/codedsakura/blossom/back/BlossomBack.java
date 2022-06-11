package dev.codedsakura.blossom.back;

import dev.codedsakura.blossom.lib.ConfigManager;
import dev.codedsakura.blossom.lib.CustomLogger;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.core.Logger;

public class BlossomBack implements ModInitializer {
    static BlossomBackConfig CONFIG = ConfigManager.register(BlossomBackConfig.class, "BlossomBack.json", newConfig -> CONFIG = newConfig);
    public static final Logger LOGGER = CustomLogger.createLogger("BlossomBack");

    @Override
    public void onInitialize() {
    }
}
