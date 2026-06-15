package es.germich3.movablechat;

import es.germich3.movablechat.config.MovableChatConfig;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovableChat implements ModInitializer {

	public static final String MOD_ID = "movablechat";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MovableChatConfig.getConfig();
		LOGGER.info("MovableChat loaded successfully");
	}
}
