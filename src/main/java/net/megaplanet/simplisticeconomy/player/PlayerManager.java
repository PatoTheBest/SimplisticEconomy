package net.megaplanet.simplisticeconomy.player;

import net.megaplanet.simplisticeconomy.SimplisticEconomy;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
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
    public void onPlayerLogin(AsyncPlayerPreLoginEvent event) {
        plugin.getStorageManager().getStorage().loadAccount(event.getName());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if(Bukkit.getPlayerExact(event.getPlayer().getName()) != null) {
                return;
            }

            plugin.getStorageManager().getStorage().unloadAccount(event.getPlayer().getName());
        }, 20L);
    }
}
