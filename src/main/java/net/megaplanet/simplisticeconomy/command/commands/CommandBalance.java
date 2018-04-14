package net.megaplanet.simplisticeconomy.command.commands;

import net.megaplanet.simplisticeconomy.command.CommandBase;
import net.megaplanet.simplisticeconomy.command.CommandManager;
import net.megaplanet.simplisticeconomy.storage.IStorage;
import net.megaplanet.simplisticeconomy.util.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class CommandBalance extends CommandBase {

    private final CommandManager commandManager;
    private static DecimalFormat df2 = new DecimalFormat(".##");

    public CommandBalance(CommandManager commandManager) {
        super("balance", "balance-command-description", "se.balance", "[player]", 0, 1, new String[] {"bal", "money"});
        this.commandManager = commandManager;
    }

    @Override
    public void onCommand(CommandSender commandSender, String[] args) {
        String playerToCheck = args.length == 1 ? args[0] : commandSender.getName();
        boolean ownBalance = commandSender.getName().equalsIgnoreCase(playerToCheck);
        IStorage storage = commandManager.getPlugin().getStorageManager().getStorage();

        double balance = storage.getBalance(playerToCheck);
        String formattedBalance = df2.format(balance);
        commandSender.sendMessage(commandManager.getMessagesFile().getMessage(ownBalance ? "balance-command-own" : "balance-command-other")
        .replace("%player%", playerToCheck)
        .replace("%amount%", formattedBalance)
        .replace("%currency%", commandManager.getPlugin().getStorageManager().getCurrencyPlural()));
    }
}
