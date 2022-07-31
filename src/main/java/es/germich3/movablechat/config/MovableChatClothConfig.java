package es.germich3.movablechat.config;

import es.germich3.movablechat.MovableChat;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = MovableChat.MOD_ID)
public class MovableChatClothConfig implements ConfigData {

    @ConfigEntry.Category("Automatic")
    @ConfigEntry.Gui.PrefixText
    boolean isAbsorptionAutoMoveEnabled;

    @ConfigEntry.Category("Manual")
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.BoundedDiscrete(min = -25, max = 795)
    int verticalityChat;

    public MovableChatClothConfig() {
        MovableChatDefaultConfig defaultConfig = new MovableChatDefaultConfig();
        this.isAbsorptionAutoMoveEnabled = defaultConfig.isAbsorptionAutoMoveEnabled;
        this.verticalityChat = defaultConfig.verticalityChat;
    }

}
