package dev.codedsakura.blossom.back;


import dev.codedsakura.blossom.lib.teleport.TeleportConfig;
import org.jetbrains.annotations.Nullable;


public class BlossomBackConfig {
    static class TeleportProps {
        TeleportConfig teleportation = null;

        int standStill;
        int cooldown;

        public TeleportProps(int standStill, int cooldown) {
            this.standStill = standStill;
            this.cooldown = cooldown;
        }
    }

    TeleportProps back = new TeleportProps(5, 120);
    @Nullable
    TeleportProps lastDeath = null;
}
