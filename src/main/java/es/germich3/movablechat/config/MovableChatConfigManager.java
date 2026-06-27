package es.germich3.movablechat.config;

import es.germich3.exceptions.DataStorageException;
import es.germich3.json.JsonManager;
import es.germich3.movablechat.MovableChat;
import es.germich3.movablechat.config.provider.MovableChatConfig;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Files;
import java.nio.file.Path;

public class MovableChatConfigManager {

    private static MovableChatConfigManager instance;

    private final MovableChatConfig config;

    private MovableChatConfigManager() {
        this.config = loadConfig();
    }

    public static synchronized void init() {
        if (instance == null) {
            instance = new MovableChatConfigManager();
        }
    }

    public static synchronized MovableChatConfigManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("MovableChatConfigManager has not been initialized. Call init() before using getInstance().");
        }
        return instance;
    }

    public static synchronized MovableChatConfig getConfig() {
        return getInstance().config;
    }

    private static MovableChatConfig loadConfig() {
        try {
            Path configDir = FabricLoader.getInstance().getConfigDir();
            if (!Files.exists(configDir.resolve(MovableChat.MOD_ID.concat(".json")))) {
                JsonManager.save(FabricLoader.getInstance().getConfigDir(), MovableChat.MOD_ID, new MovableChatConfig());
            }
            return JsonManager.load(configDir, MovableChat.MOD_ID, MovableChatConfig.class);
        }
        catch (DataStorageException e) {
            return new MovableChatConfig();
        }
    }

    public static void save() {
        try {
            JsonManager.save(FabricLoader.getInstance().getConfigDir(), MovableChat.MOD_ID, getConfig());
        }
        catch (DataStorageException e) {
            MovableChat.LOGGER.error("Failed to save config", e);
        }
    }


}
