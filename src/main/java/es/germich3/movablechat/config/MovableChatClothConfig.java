package es.germich3.movablechat.config;

import es.germich3.movablechat.MovableChat;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = MovableChat.MOD_ID)
public class MovableChatClothConfig implements ConfigData {

    @ConfigEntry.Category("automatic")
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.TransitiveObject
    public Automatic automatic = new Automatic();

    @ConfigEntry.Category("manual")
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.TransitiveObject
    public Manual manual = new Manual();

    public static class Automatic {
        public boolean isAbsorptionAutoMoveEnabled = true;
    }

    public static class Manual {
        @ConfigEntry.BoundedDiscrete(min = 0, max = 50)
        public int verticalityChat = 0;

        @ConfigEntry.BoundedDiscrete(min = 1, max = 20)
        public int multiplierChat = 1;

        @ConfigEntry.BoundedDiscrete(min = -25, max = 25)
        public int plusChat = 0;
    }

}
