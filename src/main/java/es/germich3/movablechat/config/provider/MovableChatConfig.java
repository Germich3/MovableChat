package es.germich3.movablechat.config.provider;

public class MovableChatConfig implements MovableChatConfigProvider {
    boolean isAbsorptionAutoMoveEnabled = false;
    int verticalityChat = 0;
    int multiplierChat = 1;
    int plusChat = 0;
    boolean isRecalcByArmorAbsorption = true;

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

    @Override
    public void setAbsorptionAutoMoveEnabled(boolean value) {
        isAbsorptionAutoMoveEnabled = value;
    }

    @Override
    public void setVerticalityChat(int value) {
        verticalityChat = value;
    }

    @Override
    public void setMultiplierChat(int value) {
        multiplierChat = value;
    }

    @Override
    public void setPlusChat(int value) {
        plusChat = value;
    }

    @Override
    public void setRecalcByArmorAbsorption(boolean value) {
        isRecalcByArmorAbsorption = value;
    }

}
