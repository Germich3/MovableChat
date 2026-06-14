package es.germich3.movablechat.client.apis.clothconfig;

import es.germich3.movablechat.client.config.MovableChatClothConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.AutoConfigClient;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screens.Screen;

public class ClothConfigImpl {

    public static boolean isInstalled() {
        return FabricLoader.getInstance().isModLoaded("cloth-config2");
    }

    public static MovableChatClothConfig loadConfig() {
        return AutoConfig.register(MovableChatClothConfig.class, JanksonConfigSerializer::new).getConfig();
    }

    public static Screen getConfigScreen(Screen parent) {
        return AutoConfigClient.getConfigScreen(MovableChatClothConfig.class, parent).get();
    }

}
