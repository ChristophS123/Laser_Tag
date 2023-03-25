package de.christoph.lasertag.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MessagesBuilder {

    private FileConfiguration fileConfiguration;
    private File file;
    private boolean musstSetDefaults;

    public MessagesBuilder(String name, File path) {
        file = new File(path, name);
        if(!file.exists()) {
            path.mkdirs();
            try {
                file.createNewFile();
                musstSetDefaults = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            musstSetDefaults = false;
        fileConfiguration = new YamlConfiguration();
        try {
            fileConfiguration.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        if(musstSetDefaults) {
            fileConfiguration.set("prefix", "&7[&6LaserTag§7] ");
            fileConfiguration.set("game_countdown_message", "&athe game ends in &6%time% second(s)&a.");
            fileConfiguration.set("no_player", "&cyou must be a player.");
            fileConfiguration.set("no_permission", "&cyou do not have permissions.");
            fileConfiguration.set("setup_usage", "&cuse: &6/setup setlobbyspawn &cor &6/setup setgamespawn");
            fileConfiguration.set("setup_spawn", "&athe spawn is now on your location.");
            fileConfiguration.set("join_message", "&7[&a+&7] &a%player%");
            fileConfiguration.set("leave_message", "&7[&c-&7] &a%player%");
            fileConfiguration.set("setup_not_finished", "&cPlease finish the setup.");
            fileConfiguration.set("game_started", "&4&lGame has started");
            fileConfiguration.set("protection_time_end", "&aThe protection time ends in &6%time% seconds&a.");
            fileConfiguration.set("lobby_time_end", "&aThe game starts in &6%time% seconds&a.");
            fileConfiguration.set("got_killed", "&cYou have been killed by &6%player%");
            fileConfiguration.set("killed_player", "&aYou have killed the player &6%player%");
            fileConfiguration.set("server_restart_in", "&cThe server will restart in &6%time%&c.");
            fileConfiguration.set("scoreboard_title", "&7&l● &a&lLaserTag &7&l●");
            fileConfiguration.set("waiting_for_players", "&6 > Waiting for players");
            save();
        }
    }

    public String getString(String path) {
        String string = ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString(path));
        return string;
    }

    public void save() {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            fileConfiguration.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

}