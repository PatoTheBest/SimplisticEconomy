package net.megaplanet.simplisticeconomy;

import net.megaplanet.simplisticeconomy.files.FileManager;
import net.megaplanet.simplisticeconomy.storage.StorageManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SimplisticEconomy extends JavaPlugin {

    private final FileManager fileManager = new FileManager(this);
    private final StorageManager storageManager = new StorageManager(this);

    @Override
    public void onEnable() {
        fileManager.load();
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public StorageManager getStorageManager() {
        return storageManager;
    }
}
