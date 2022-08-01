package es.germich3.movablechat.config;

import java.lang.reflect.Field;

public class MovableChatConfig {

    private final Object config;

    public MovableChatConfig() {
        if (ClothConfigImpl.isInstalled()) {
            config = ClothConfigImpl.loadConfig();
        }
        else {
            config = new MovableChatDefaultConfig();
        }
    }

    public boolean isAbsorptionAutoMoveEnabled() {
        try {
            Field field = config.getClass().getDeclaredField("isAbsorptionAutoMoveEnabled");
            return (boolean) field.get(config);
        } catch (Exception e) {
            return new MovableChatDefaultConfig().isAbsorptionAutoMoveEnabled;
        }
    }

    public int getVerticalityChat() {
        try {
            Field field = config.getClass().getDeclaredField("verticalityChat");
            return (int) field.get(config);
        } catch (Exception e) {
            return new MovableChatDefaultConfig().verticalityChat;
        }
    }

    public int getMultiplierChat() {
        try {
            Field field = config.getClass().getDeclaredField("multiplierChat");
            return (int) field.get(config);
        } catch (Exception e) {
            return new MovableChatDefaultConfig().multiplierChat;
        }
    }

    public int getPlusChat() {
        try {
            Field field = config.getClass().getDeclaredField("plusChat");
            return (int) field.get(config);
        } catch (Exception e) {
            return new MovableChatDefaultConfig().plusChat;
        }
    }

}
