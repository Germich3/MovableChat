package es.germich3.movablechat.screens.api.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import es.germich3.movablechat.api.clothconfig.ClothConfig;
import es.germich3.movablechat.screens.config.MissingClothConfigScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuConfigScreen implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            if (ClothConfig.isInstalled()) {
                return ClothConfig.getConfigScreen(parent);
            } else {
                return new MissingClothConfigScreen(parent);
            }
        };
    }
}
