package es.germich3.movablechat.config;

import es.germich3.movablechat.MovableChat;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.loader.api.FabricLoader;

public class ClothConfigImpl {

    public static void loadConfig() {
        if (FabricLoader.getInstance().isModLoaded("cloth-config2")) {
            MovableChat.CONFIG = AutoConfig.register(MovableChatConfig.class, JanksonConfigSerializer::new).getConfig();
        }
    }

}
