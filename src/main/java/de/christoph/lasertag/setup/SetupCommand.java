package de.christoph.lasertag.setup;

import de.christoph.lasertag.Constants;
import de.christoph.lasertag.LaserTag;
import de.christoph.lasertag.utils.LocationUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SetupCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(player.hasPermission("lasertag.admin")) {
                if(strings.length == 1) {
                    if(strings[0].equalsIgnoreCase("setlobbyspawn")) {
                        LocationUtil.saveLocation("lobbyspawn", LaserTag.getPlugin(), player.getLocation());
                        player.sendMessage(Constants.PREFIX + Constants.SETUP_SPAWN);
                    } else if(strings[0].equalsIgnoreCase("setgamespawn")) {
                        LocationUtil.saveLocation("gamespawn", LaserTag.getPlugin(), player.getLocation());
                        player.sendMessage(Constants.PREFIX + Constants.SETUP_SPAWN);
                    } else
                        player.sendMessage(Constants.PREFIX + Constants.SETUP_USAGE);
                } else
                    player.sendMessage(Constants.PREFIX + Constants.SETUP_USAGE);
            } else
                player.sendMessage(Constants.PREFIX + Constants.NO_PERMISSION);
        } else
            commandSender.sendMessage(Constants.PREFIX + Constants.NO_PLAYER);
        return false;
    }

}
