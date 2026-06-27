package es.germich3.movablechat.config.provider;

public interface MovableChatConfigProvider {

    boolean isAbsorptionAutoMoveEnabled();
    int getVerticalityChat();
    int getMultiplierChat();
    int getPlusChat();
    boolean isRecalcByArmorAbsorption();

    void setAbsorptionAutoMoveEnabled(boolean value);
    void setVerticalityChat(int value);
    void setMultiplierChat(int value);
    void setPlusChat(int value);
    void setRecalcByArmorAbsorption(boolean value);
}
