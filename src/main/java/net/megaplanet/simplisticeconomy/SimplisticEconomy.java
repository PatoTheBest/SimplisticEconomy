package net.megaplanet.simplisticeconomy;

import net.megaplanet.simplisticeconomy.files.FileManager;
import net.megaplanet.simplisticeconomy.player.PlayerManager;
import net.megaplanet.simplisticeconomy.storage.StorageManager;
import net.megaplanet.simplisticeconomy.vault.VaultManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SimplisticEconomy extends JavaPlugin {

    private final FileManager fileManager = new FileManager(this);
    private final StorageManager storageManager = new StorageManager(this);
    private final PlayerManager playerManager = new PlayerManager(this);
    private final VaultManager vaultManager = new VaultManager(this);

    @Override
    public void onEnable() {
        fileManager.load();
        storageManager.load();
        playerManager.load();
        vaultManager.attemptRegister();
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public StorageManager getStorageManager() {
        return storageManager;
    }
}
