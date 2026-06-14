package es.germich3.movablechat.client;

import es.germich3.movablechat.client.config.MovableChatConfig;
import net.fabricmc.api.ClientModInitializer;

public class MovableChatClient implements ClientModInitializer {

	public static final String MOD_ID = "movablechat";

	@Override
	public void onInitializeClient() {
		MovableChatConfig.getConfig();
	}

}
