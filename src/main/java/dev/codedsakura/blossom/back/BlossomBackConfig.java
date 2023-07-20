package dev.codedsakura.blossom.back;


import dev.codedsakura.blossom.lib.teleport.TeleportConfig;


public class BlossomBackConfig {
    static class TeleportProps {
        boolean enabled = true;

        TeleportConfig teleportation = null;

        int standStill = 5;
        int cooldown = 120;

        TeleportProps() {
            // For default values in GSON serialization
        }

        public TeleportProps(boolean enabled, int standStill, int cooldown) {
            this.enabled = enabled;
            this.standStill = standStill;
            this.cooldown = cooldown;
        }
    }

    TeleportProps back = new TeleportProps(true, 5, 120);

    TeleportProps lastDeath = new TeleportProps(false, 5, 120);

    boolean separateCooldowns = false;

    boolean persistBack = false;
    boolean persistLastDeath = false;
}
