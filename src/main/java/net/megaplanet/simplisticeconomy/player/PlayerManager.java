package net.megaplanet.simplisticeconomy.player;

import net.megaplanet.simplisticeconomy.SimplisticEconomy;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerManager implements Listener {

    private final SimplisticEconomy plugin;

    public PlayerManager(SimplisticEconomy plugin) {
        this.plugin = plugin;
    }

    public void load() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLogin(PlayerLoginEvent event) {
        plugin.getStorageManager().getStorage().loadAccount(event.getPlayer().getName());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        plugin.getStorageManager().getStorage().unloadAccount(event.getPlayer().getName());
    }
}
