package es.germich3.movablechat.client.apis.clothconfig;

import es.germich3.movablechat.config.provider.MovableChatClothConfig;
import me.shedaniel.autoconfig.AutoConfigClient;
import net.minecraft.client.gui.screens.Screen;

public class ClothConfigClientApi {

    public static Screen getConfigScreen(Screen parent) {
        return AutoConfigClient.getConfigScreen(MovableChatClothConfig.class, parent).get();
    }

}
