package net.megaplanet.simplisticeconomy.vault;

import net.megaplanet.simplisticeconomy.SimplisticEconomy;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

public class VaultManager {

    private final SimplisticEconomy plugin;

    public VaultManager(SimplisticEconomy plugin) {
        this.plugin = plugin;
    }

    public void attemptRegister() {
        if(!plugin.getServer().getPluginManager().isPluginEnabled("Vault")) {
            return;
        }

        ServicesManager servicesManager = plugin.getServer().getServicesManager();
        servicesManager.register(Economy.class, new VaultIntegration(plugin), plugin, ServicePriority.High);
    }
}
