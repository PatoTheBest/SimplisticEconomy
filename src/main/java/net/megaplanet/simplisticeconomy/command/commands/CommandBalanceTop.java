package net.megaplanet.simplisticeconomy.command.commands;

import net.megaplanet.simplisticeconomy.command.CommandBase;
import net.megaplanet.simplisticeconomy.command.CommandManager;
import net.megaplanet.simplisticeconomy.storage.IStorage;
import org.bukkit.command.CommandSender;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CommandBalanceTop extends CommandBase {

    private final CommandManager commandManager;
    private static DecimalFormat df2 = new DecimalFormat(".##");

    public CommandBalanceTop(CommandManager commandManager) {
        super("balancetop", "balance-top-command-description", "se.balancetop", null, 0, 0, new String[] {"baltop", "moneytop"});
        this.commandManager = commandManager;
    }

    @Override
    public void onCommand(CommandSender commandSender, String[] args) {
        IStorage storage = commandManager.getPlugin().getStorageManager().getStorage();
        Map<String, Double> topBalance = storage.getTopBalance();

        commandSender.sendMessage(commandManager.getMessagesFile().getMessage("balance-top-command-header"));
        AtomicInteger index = new AtomicInteger(1);

        topBalance.forEach((player, balance) -> {
            String formattedBalance = df2.format(balance);
            commandSender.sendMessage(commandManager.getMessagesFile().getMessage("balance-top-command-player")
                    .replace("%index%", index.getAndIncrement() + "")
                    .replace("%player%", player)
                    .replace("%balance%", formattedBalance)
                    .replace("%currency%", commandManager.getPlugin().getStorageManager().getCurrencyPlural()));
        });
    }
}
