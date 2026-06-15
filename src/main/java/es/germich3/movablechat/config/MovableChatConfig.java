package es.germich3.movablechat.config;

import es.germich3.movablechat.apis.clothconfig.ClothConfigApi;
import es.germich3.movablechat.config.provider.MovableChatConfigProvider;
import es.germich3.movablechat.config.provider.MovableChatDefaultConfig;

public class MovableChatConfig {

    private static MovableChatConfig singleton;

    private final MovableChatConfigProvider config;

    private MovableChatConfig() {
        if (ClothConfigApi.isInstalled()) {
            config = ClothConfigApi.loadConfig();
        }
        else {
            config = new MovableChatDefaultConfig();
        }
    }

    public static MovableChatConfigProvider getConfig() {
        if (singleton == null) {
            singleton = new MovableChatConfig();
        }
        return singleton.config;
    }

}
