package es.germich3.movablechat.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.loader.api.FabricLoader;

import java.lang.reflect.Field;

public class MovableChatConfig {

    private final Object config;

    public MovableChatConfig() {
        if (FabricLoader.getInstance().isModLoaded("cloth-config2")) {
            config = AutoConfig.register(MovableChatClothConfig.class, JanksonConfigSerializer::new).getConfig();
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
