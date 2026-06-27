package es.germich3.movablechat;

import es.germich3.movablechat.config.MovableChatConfigManager;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovableChat implements ModInitializer {

	public static final String MOD_ID = "movablechat";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MovableChatConfigManager.init();
		LOGGER.info("MovableChat loaded successfully");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

}
