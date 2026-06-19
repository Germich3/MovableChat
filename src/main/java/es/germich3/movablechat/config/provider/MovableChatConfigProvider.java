package es.germich3.movablechat.config.provider;

public interface MovableChatConfigProvider {

    boolean isAbsorptionAutoMoveEnabled();
    int getVerticalityChat();
    int getMultiplierChat();
    int getPlusChat();
    boolean isRecalcByArmorAbsorption();

}
