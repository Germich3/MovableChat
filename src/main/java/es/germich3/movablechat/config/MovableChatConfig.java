package es.germich3.movablechat.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "movablechat")
public class MovableChatConfig implements ConfigData {

    @ConfigEntry.Category("Automatic")
    @ConfigEntry.Gui.PrefixText
    public boolean absorptionAutoMove = false;

    @ConfigEntry.Category("Manual")
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.BoundedDiscrete(min = 0, max = 60)
    public int verticalityChat = 0;

}
