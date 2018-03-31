package net.megaplanet.simplisticeconomy.storage;

import net.megaplanet.simplisticeconomy.SimplisticEconomy;
import net.megaplanet.simplisticeconomy.files.ConfigFile;
import net.megaplanet.simplisticeconomy.storage.types.mysql.MySQLStorage;
import net.megaplanet.simplisticeconomy.storage.types.NullStorage;
import net.megaplanet.simplisticeconomy.util.Utils;

public class StorageManager {

    private final SimplisticEconomy plugin;
    private boolean useUUIDs = true;
    private IStorage storage;
    private String currencySingular;
    private String currencyPlural;

    public StorageManager(SimplisticEconomy plugin) {
        this.plugin = plugin;
    }

    public void load() {
        ConfigFile config = plugin.getFileManager().getConfigFile();

        StorageType storageType = Utils.getEnumValueFromString(StorageType.class, config.getString("storage.type"));
        useUUIDs = config.getBoolean("storage.use-uuids");
        currencySingular = config.getString("currency.currency-singular");
        currencyPlural = config.getString("currency.currency-singular");

        if (storageType == null) {
            // unknown storage type
            // fallback into no storage type
            storageType = StorageType.NONE;
        }

        switch (storageType) {
            case MYSQL:
                storage = new MySQLStorage(this);
                break;
            case NONE:
                storage = new NullStorage();
                break;
        }
    }

    public IStorage getStorage() {
        return storage;
    }

    public SimplisticEconomy getPlugin() {
        return plugin;
    }

    public boolean isUseUUIDs() {
        return useUUIDs;
    }

    public String getCurrencySingular() {
        return currencySingular;
    }

    public String getCurrencyPlural() {
        return currencyPlural;
    }
}
