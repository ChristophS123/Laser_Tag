package de.christoph.lasertag.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SettingsBuilder {

    private FileConfiguration fileConfiguration;
    private File file;
    private boolean musstSetDefaults;

    public SettingsBuilder(String name, File path) {
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
            fileConfiguration.set("laser_weapon_name", "&c&lWeapon");
            fileConfiguration.set("lobby_countdown_seconds", 20);
            fileConfiguration.set("game_time", 120);
            fileConfiguration.set("protection_time", 10);
            fileConfiguration.set("restart_time", 20);
            fileConfiguration.set("min_players", 4);
            fileConfiguration.set("max_players", 10);
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
