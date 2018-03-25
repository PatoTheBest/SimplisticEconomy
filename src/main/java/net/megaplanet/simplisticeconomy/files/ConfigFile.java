package net.megaplanet.simplisticeconomy.files;

import org.bukkit.plugin.Plugin;

public class ConfigFile extends AbstractFile {

    public ConfigFile(Plugin plugin) {
        super(plugin, "config.yml");
    }
}
