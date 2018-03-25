package net.megaplanet.simplisticeconomy.files;

import net.megaplanet.simplisticeconomy.SimplisticEconomy;

public class FileManager {

    private final ConfigFile configFile;
    private final MessagesFile messagesFile;

    public FileManager(SimplisticEconomy plugin) {
        this.configFile = new ConfigFile(plugin);
        this.messagesFile = new MessagesFile(plugin);
    }

    public void load() {
        configFile.load();
        messagesFile.load();
    }

    public ConfigFile getConfigFile() {
        return configFile;
    }

    public MessagesFile getMessagesFile() {
        return messagesFile;
    }
}
