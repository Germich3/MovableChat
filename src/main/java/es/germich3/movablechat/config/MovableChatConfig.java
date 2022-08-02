package es.germich3.movablechat.config;

import es.germich3.movablechat.api.clothconfig.ClothConfig;

public class MovableChatConfig {

    private static final MovableChatConfig singleton = new MovableChatConfig();

    private final MovableChatClothConfig config;

    private MovableChatConfig() {
        if (ClothConfig.isInstalled()) {
            config = ClothConfig.loadConfig();
        }
        else {
            config = new MovableChatClothConfig();
        }
    }

    public static MovableChatClothConfig getConfig() {
        return singleton.config;
    }

}
