package es.germich3.movablechat.client.apis.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import es.germich3.movablechat.apis.clothconfig.ClothConfigApi;
import es.germich3.movablechat.client.apis.clothconfig.ClothConfigClientApi;
import es.germich3.movablechat.client.screens.MissingClothConfigScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            if (ClothConfigApi.isInstalled()) {
                return ClothConfigClientApi.getConfigScreen(parent);
            } else {
                return new MissingClothConfigScreen(parent);
            }
        };
    }
}
