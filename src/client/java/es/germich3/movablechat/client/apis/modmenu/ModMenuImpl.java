package es.germich3.movablechat.client.apis.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import es.germich3.movablechat.apis.clothconfig.ClothConfigApi;
import es.germich3.movablechat.client.apis.clothconfig.ClothConfigClientApi;
import es.germich3.movablechat.client.screens.MissingClothConfigScreen;
import es.germich3.movablechat.utils.Utils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.options.ChatOptionsScreen;

@Environment(EnvType.CLIENT)
public class ModMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            if (Utils.checkIfClassExist("es.germich3.json.JsonManager")) {
                return new ChatOptionsScreen(parent, Minecraft.getInstance().options);
            }
            else if (ClothConfigApi.isInstalled()) {
                return ClothConfigClientApi.getConfigScreen(parent);
            }
            else {
                return new MissingClothConfigScreen(parent);
            }
        };
    }
}
