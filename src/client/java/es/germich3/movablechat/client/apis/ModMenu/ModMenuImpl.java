package es.germich3.movablechat.client.apis.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import es.germich3.movablechat.client.apis.clothconfig.ClothConfigImpl;
import es.germich3.movablechat.client.screens.MissingClothConfigScreen;
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
