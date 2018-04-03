package net.megaplanet.simplisticeconomy.command.commands;

import net.megaplanet.simplisticeconomy.command.CommandBase;
import net.megaplanet.simplisticeconomy.command.CommandManager;
import net.megaplanet.simplisticeconomy.storage.IStorage;
import net.megaplanet.simplisticeconomy.util.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBalance extends CommandBase {

    private final CommandManager commandManager;

    public CommandBalance(CommandManager commandManager) {
        super("balance", "balance-command-description", "se.balance", /*"[player]"*/ "", 0, 1, new String[] {"bal", "money"});
        this.commandManager = commandManager;
    }

    @Override
    public void onCommand(CommandSender commandSender, String[] args) {
        Player player = CommandUtils.getPlayer(commandSender);
        IStorage storage = commandManager.getPlugin().getStorageManager().getStorage();

        double balance = storage.getBalance(player.getName());
        player.sendMessage(commandManager.getMessagesFile().getMessage("balance-command-info")
        .replace("%amount%", balance + "")
        .replace("%currency%", commandManager.getPlugin().getStorageManager().getCurrencyPlural()));
    }
}
