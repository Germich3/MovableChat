package es.germich3.movablechat;

import es.germich3.movablechat.config.MovableChatConfig;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovableChat implements ModInitializer {

	public static final String MOD_ID = "movablechat";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final MovableChatConfig CONFIG = new MovableChatConfig();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("MovableChat loaded successfully");
	}
}
