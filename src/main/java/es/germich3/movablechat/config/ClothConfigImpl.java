package es.germich3.movablechat.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;

public class ClothConfigImpl {

    public static boolean isInstalled() {
        return FabricLoader.getInstance().isModLoaded("cloth-config2");
    }

    public static MovableChatClothConfig loadConfig() {
        return AutoConfig.register(MovableChatClothConfig.class, JanksonConfigSerializer::new).getConfig();
    }

    public static Screen getConfigScreen(Screen parent) {
        return AutoConfig.getConfigScreen(MovableChatClothConfig.class, parent).get();
    }

}
