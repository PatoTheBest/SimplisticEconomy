package net.megaplanet.simplisticeconomy.command.commands;

import net.megaplanet.simplisticeconomy.command.CommandBase;
import net.megaplanet.simplisticeconomy.command.CommandManager;
import net.megaplanet.simplisticeconomy.storage.IStorage;
import net.megaplanet.simplisticeconomy.util.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSet extends CommandBase {

    private final CommandManager commandManager;

    public CommandSet(CommandManager commandManager) {
        super("set", "set-command-description", "se.set", "<player> <amount>", 2, 2);
        this.commandManager = commandManager;
    }

    @Override
    public void onCommand(CommandSender commandSender, String[] args) {
        IStorage storage = commandManager.getPlugin().getStorageManager().getStorage();
        int amount = CommandUtils.getInt(args, 1);

        storage.setPlayerBalance(args[0], amount);
        commandSender.sendMessage(commandManager.getMessagesFile().getMessage("set-command-set").replace("%amount%", amount + "").replace("%player%", args[0]));

        Player receiver = Bukkit.getPlayerExact(args[1]);
        if(receiver != null) {
            receiver.sendMessage(commandManager.getMessagesFile().getMessage("set-command-received").replace("%amount%", amount + "").replace("%player%", commandSender.getName()));
        }
    }
}
