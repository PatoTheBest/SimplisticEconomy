package net.megaplanet.simplisticeconomy.util;

import net.megaplanet.simplisticeconomy.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandUtils {

    public static void validateNotNull(Object object, String message) throws CommandException {
        if(object == null) {
            throw new CommandException(message);
        }
    }

    public static void validateTrue(boolean isTrue, String message) throws CommandException {
        if(!isTrue) {
            throw new CommandException(message);
        }
    }

    public static Player getPlayer(CommandSender sender) throws CommandException {
        if(sender instanceof Player) {
            return (Player) sender;
        }

        throw new CommandException("no-console");
    }

    public static int getInt(String[] args, int pos) throws CommandException {
        try {
            return Integer.parseInt(args[pos]);
        } catch (NumberFormatException e) {
            throw new CommandException("not-a-number");
        }
    }
}
