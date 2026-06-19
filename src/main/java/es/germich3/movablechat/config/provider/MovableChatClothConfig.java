package es.germich3.movablechat.config.provider;

import es.germich3.movablechat.MovableChat;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = MovableChat.MOD_ID)
public class MovableChatClothConfig implements MovableChatConfigProvider, ConfigData {

    @ConfigEntry.Category("automatic")
    @ConfigEntry.Gui.PrefixText
    boolean isAbsorptionAutoMoveEnabled;

    @ConfigEntry.Category("manual")
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.BoundedDiscrete(min = 0, max = 50)
    int verticalityChat;
    @ConfigEntry.Category("manual")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 20)
    int multiplierChat;
    @ConfigEntry.Category("manual")
    @ConfigEntry.BoundedDiscrete(min = -25, max = 25)
    int plusChat;
    @ConfigEntry.Category("manual")
    boolean isRecalcByArmorAbsorption;

    public MovableChatClothConfig() {
        MovableChatDefaultConfig defaultConfig = new MovableChatDefaultConfig();
        this.isAbsorptionAutoMoveEnabled = defaultConfig.isAbsorptionAutoMoveEnabled;
        this.verticalityChat = defaultConfig.verticalityChat;
        this.multiplierChat = defaultConfig.multiplierChat;
        this.plusChat = defaultConfig.plusChat;
        this.isRecalcByArmorAbsorption = defaultConfig.isRecalcByArmorAbsorption;
    }

    @Override
    public boolean isAbsorptionAutoMoveEnabled() {
        return isAbsorptionAutoMoveEnabled;
    }

    @Override
    public int getVerticalityChat() {
        return verticalityChat;
    }

    @Override
    public int getMultiplierChat() {
        return multiplierChat;
    }

    @Override
    public int getPlusChat() {
        return plusChat;
    }

    @Override
    public boolean isRecalcByArmorAbsorption() {
        return isRecalcByArmorAbsorption;
    }

}
