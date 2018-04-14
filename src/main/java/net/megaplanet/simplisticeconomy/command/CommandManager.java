package net.megaplanet.simplisticeconomy.command;

import net.megaplanet.simplisticeconomy.SimplisticEconomy;
import net.megaplanet.simplisticeconomy.command.commands.*;
import net.megaplanet.simplisticeconomy.files.MessagesFile;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager implements CommandExecutor {

    private final SimplisticEconomy plugin;
    private final MessagesFile messagesFile;
    private final List<CommandBase> commands = new ArrayList<>();

    public CommandManager(SimplisticEconomy plugin) {
        this.plugin = plugin;
        this.messagesFile = plugin.getFileManager().getMessagesFile();

        // user commands
        this.commands.add(new CommandPay(this));
        this.commands.add(new CommandBalance(this));
        this.commands.add(new CommandBalanceTop(this));

        // admin commands
        this.commands.add(new CommandGive(this));
        this.commands.add(new CommandSet(this));
    }

    public void register() {
        plugin.getCommand("pay").setExecutor(this);
        plugin.getCommand("money").setExecutor(this);
        plugin.getCommand("bal").setExecutor(this);
        plugin.getCommand("balance").setExecutor(this);
        plugin.getCommand("eco").setExecutor(this);
        plugin.getCommand("balancetop").setExecutor(this);
        plugin.getCommand("baltop").setExecutor(this);
        plugin.getCommand("moneytop").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] args) {
        for (CommandBase commandBase : commands) {
            if(commandBase.getCommand().equalsIgnoreCase(commandLabel)) {
                return execute(commandSender, commandBase, args, null);
            }

            for (String alias : commandBase.getAliases()) {
                if(alias.equalsIgnoreCase(commandLabel)) {
                    return execute(commandSender, commandBase, args, null);
                }
            }
        }

        if(args.length == 0) {
            // display help
            commandSender.sendMessage("help todo");
            return false;
        }

        List<String> arguments = new ArrayList<>(Arrays.asList(args));
        arguments.remove(0);

        for (CommandBase commandBase : commands) {
            if(commandBase.getCommand().equalsIgnoreCase(args[0])) {
                return execute(commandSender, commandBase, arguments.toArray(new String[arguments.size()]), commandLabel);
            }

            for (String alias : commandBase.getAliases()) {
                if(alias.equalsIgnoreCase(commandLabel)) {
                    return execute(commandSender, commandBase, arguments.toArray(new String[arguments.size()]), commandLabel);
                }
            }
        }

        // display help
        commandSender.sendMessage("help todo");
        return false;
    }

    private boolean execute(CommandSender commandSender, CommandBase command, String[] args, String parentCommand) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                if(!commandSender.hasPermission(command.getPermission())) {
                    commandSender.sendMessage(messagesFile.getMessage("no-permission"));
                    return;
                }

                if (args.length < command.getMin()) {
                    commandSender.sendMessage(messagesFile.getMessage("too-few-arguments"));
                    commandSender.sendMessage(messagesFile.getMessage("usage")
                            .replace("%usage%", "/" + (parentCommand != null ? parentCommand + " " : "") + command.getCommand() + " " + command.getUsage()));
                    return;
                }

                if (command.getMax() != -1 && args.length > command.getMax()) {
                    commandSender.sendMessage(messagesFile.getMessage("too-many-arguments"));
                    commandSender.sendMessage(messagesFile.getMessage("usage")
                            .replace("%usage%", "/" + (parentCommand != null ? parentCommand + " " : "") + command.getCommand() + " " + command.getUsage()));
                    return;
                }

                command.onCommand(commandSender, args);
            } catch (CommandException commandException) {
                commandSender.sendMessage(messagesFile.getMessage(commandException.getMessage()));
            } catch (Exception e) {
                commandSender.sendMessage(messagesFile.getMessage("something-went-wrong"));
                e.printStackTrace();
            }
        });
        return true;
    }

    public SimplisticEconomy getPlugin() {
        return plugin;
    }

    public MessagesFile getMessagesFile() {
        return messagesFile;
    }
}
