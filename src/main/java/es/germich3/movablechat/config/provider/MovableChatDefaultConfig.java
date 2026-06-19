package es.germich3.movablechat.config.provider;

public class MovableChatDefaultConfig implements MovableChatConfigProvider {
    final boolean isAbsorptionAutoMoveEnabled = true;
    final int verticalityChat = 0;
    final int multiplierChat = 1;
    final int plusChat = 0;
    final boolean isRecalcByArmorAbsorption = false;

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
