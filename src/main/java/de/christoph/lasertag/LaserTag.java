package de.christoph.lasertag;

import de.christoph.lasertag.protection.ProtectionListeners;
import de.christoph.lasertag.setup.SetupCommand;
import de.christoph.lasertag.state.StateManager;
import de.christoph.lasertag.state.game.GameListeners;
import de.christoph.lasertag.state.game.weapon.LaserWeapon;
import de.christoph.lasertag.state.lobby.LobbyListeners;
import de.christoph.lasertag.utils.MessagesBuilder;
import de.christoph.lasertag.utils.SettingsBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class LaserTag extends JavaPlugin {

    private static LaserTag plugin;
    private StateManager stateManager;
    private MessagesBuilder messages;
    private SettingsBuilder settingsBuilder;

    public static ArrayList<Player> spectator = new ArrayList<>();
    public static HashMap<Player, Integer> gamePlayers = new HashMap<>(); // Integer for player kills

    @Override
    public void onEnable() {
        plugin = this;
        stateManager = new StateManager();
        stateManager.setLobbyState();
        registerListeners(Bukkit.getPluginManager());
        registerCommands();
        setUpSettingsFile();
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                setUpMessagesFile();
            }
        }, 20*3);
    }

    private void setUpSettingsFile() {
        settingsBuilder = new SettingsBuilder("settings.yml", getDataFolder());
        Constants.LASER_WEAPON_NAME = settingsBuilder.getString("laser_weapon_name");
        Constants.LOBBY_COUNTDOWN_SECONDS = settingsBuilder.getFileConfiguration().getInt("lobby_countdown_seconds");
        Constants.GAME_TIME = settingsBuilder.getFileConfiguration().getInt("game_time");
        Constants.PROTECTION_TIME = settingsBuilder.getFileConfiguration().getInt("protection_time");
        Constants.RESTART_TIME = settingsBuilder.getFileConfiguration().getInt("restart_time");
        Constants.MIN_PLAYERS = settingsBuilder.getFileConfiguration().getInt("min_players");
        Constants.MAX_PLAYERS = settingsBuilder.getFileConfiguration().getInt("max_players");
        settingsBuilder.save();
    }

    private void setUpMessagesFile() {
        messages = new MessagesBuilder("messages.yml", getDataFolder());
        Constants.PREFIX = messages.getString("prefix");
        Constants.GAME_COUNTDOWN_MESSAGE = messages.getString("game_countdown_message");
        Constants.NO_PLAYER = messages.getString("no_player");
        Constants.NO_PERMISSION = messages.getString("no_permission");
        Constants.SETUP_USAGE = messages.getString("setup_usage");
        Constants.SETUP_SPAWN = messages.getString("setup_spawn");
        Constants.JOIN_MESSAGE = messages.getString("join_message");
        Constants.QUIT_MESSAGE = messages.getString("leave_message");
        Constants.SETUP_NOT_FINISHED = messages.getString("setup_not_finished");
        Constants.GAME_START = messages.getString("game_started");
        Constants.PROTECTION_TIME_END = messages.getString("protection_time_end");
        Constants.LOBBY_TIME_END = messages.getString("lobby_time_end");
        Constants.GOT_KILLED = messages.getString("got_killed");
        Constants.KILLED_PLAYER = messages.getString("killed_player");
        Constants.SERVER_RESTART_IN = messages.getString("server_restart_in");
        Constants.SCOREBOARD_TITLE = messages.getString("scoreboard_title");
    }

    private void registerCommands() {
        getCommand("setup").setExecutor(new SetupCommand());
    }

    private void registerListeners(PluginManager pluginManager) {
        pluginManager.registerEvents(new LobbyListeners(), this);
        pluginManager.registerEvents(new GameListeners(), this);
        pluginManager.registerEvents(new LaserWeapon(), this);
        pluginManager.registerEvents(new ProtectionListeners(), this);
    }

    public static LaserTag getPlugin() {
        return plugin;
    }

    public StateManager getStateManager() {
        return stateManager;
    }

}
