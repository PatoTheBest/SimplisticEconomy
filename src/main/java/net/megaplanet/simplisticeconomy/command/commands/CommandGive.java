package net.megaplanet.simplisticeconomy.command.commands;

import net.megaplanet.simplisticeconomy.command.CommandBase;
import net.megaplanet.simplisticeconomy.command.CommandManager;
import net.megaplanet.simplisticeconomy.storage.IStorage;
import net.megaplanet.simplisticeconomy.util.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGive extends CommandBase {

    private final CommandManager commandManager;

    public CommandGive(CommandManager commandManager) {
        super("give", "give-command-description", "se.give", "<player> <amount>", 2, 2);
        this.commandManager = commandManager;
    }

    @Override
    public void onCommand(CommandSender commandSender, String[] args) {
        IStorage storage = commandManager.getPlugin().getStorageManager().getStorage();
        int amount = CommandUtils.getInt(args, 1);

        storage.depositPlayer(args[0], amount);
        commandSender.sendMessage(commandManager.getMessagesFile().getMessage("give-command-sent").replace("%amount%", amount + "").replace("%player%", args[0]));

        Player receiver = Bukkit.getPlayerExact(args[0]);
        if(receiver != null) {
            receiver.sendMessage(commandManager.getMessagesFile().getMessage("give-command-received").replace("%amount%", amount + "").replace("%player%", commandSender.getName()));
        }
    }
}
