package net.megaplanet.simplisticeconomy.files;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class MessagesFile extends AbstractFile {

    public MessagesFile(Plugin plugin) {
        super(plugin, "messages.yml");
    }

    public String getMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&', getString(path, "MESSAGE " + path + " NOT FOUND"));
    }
}
