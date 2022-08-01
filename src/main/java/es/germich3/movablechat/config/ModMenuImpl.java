package es.germich3.movablechat.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            if (ClothConfigImpl.isInstalled()) {
                return ClothConfigImpl.getConfigScreen(parent);
            } else {
                return new MissingClothConfigScreen(parent);
            }
        };
    }
}
