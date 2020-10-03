package net.megaplanet.simplisticeconomy.command.commands;

import net.megaplanet.simplisticeconomy.command.CommandBase;
import net.megaplanet.simplisticeconomy.command.CommandManager;
import net.megaplanet.simplisticeconomy.storage.IStorage;
import net.megaplanet.simplisticeconomy.util.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPay extends CommandBase {

    private final CommandManager commandManager;

    public CommandPay(CommandManager commandManager) {
        super("pay", "pay-command-description", "se.pay", "<player> <amount>", 2, 2);
        this.commandManager = commandManager;
    }

    @Override
    public void onCommand(CommandSender commandSender, String[] args) {
        Player receiver = Bukkit.getPlayerExact(args[0]);
        Player player = CommandUtils.getPlayer(commandSender);

        IStorage storage = commandManager.getPlugin().getStorageManager().getStorage();

        if (!storage.hasAccount(args[0])) {
            player.sendMessage("§cNo player found with name '"+ args[0] +"'");
            return;
        }

        int amount = CommandUtils.getInt(args, 1);
        CommandUtils.validateTrue(amount > 0, "must-be-positive");
        CommandUtils.validateTrue(!player.getName().equalsIgnoreCase(args[0]), "you-cannot-pay-yourself");
        CommandUtils.validateTrue(storage.hasEnough(player.getName(), amount), "not-enough-money");

        storage.withdrawPlayer(player.getName(), amount);
        storage.depositPlayer(args[0], amount);
        player.sendMessage(commandManager.getMessagesFile().getMessage("pay-command-sent").replace("%amount%", amount + "").replace("%player%", args[0]));

        if(receiver != null) {
            receiver.sendMessage(commandManager.getMessagesFile().getMessage("pay-command-received").replace("%amount%", amount + "").replace("%player%", player.getName()));
        }
    }
}
