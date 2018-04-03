package net.megaplanet.simplisticeconomy.command;

import org.bukkit.command.CommandSender;

public abstract class CommandBase {

    private final String name;
    private final String description;
    private final String permission;
    private final String usage;
    private final int min;
    private final int max;

    public CommandBase(String name, String description, String permission, String usage, int min, int max) {
        this.name = name;
        this.description = description;
        this.permission = permission;
        this.usage = usage;
        this.min = min;
        this.max = max;
    }

    public abstract void onCommand(CommandSender commandSender, String[] args);

    public String getCommand() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPermission() {
        return permission;
    }

    public String getUsage() {
        return usage;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
