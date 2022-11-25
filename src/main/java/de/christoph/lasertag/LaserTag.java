package de.christoph.lasertag;

import de.christoph.lasertag.setup.SetupCommand;
import de.christoph.lasertag.state.StateManager;
import de.christoph.lasertag.state.game.GameListeners;
import de.christoph.lasertag.state.game.weapon.LaserWeapon;
import de.christoph.lasertag.state.lobby.LobbyListeners;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class LaserTag extends JavaPlugin {

    private static LaserTag plugin;
    private StateManager stateManager;

    public static ArrayList<Player> spectator = new ArrayList<>();
    public static HashMap<Player, Integer> gamePlayers = new HashMap<>(); // Integer for player kills

    @Override
    public void onEnable() {
        plugin = this;
        stateManager = new StateManager();
        stateManager.setLobbyState();
        registerListeners(Bukkit.getPluginManager());
        registerCommands();
    }

    private void registerCommands() {
        getCommand("setup").setExecutor(new SetupCommand());
    }

    private void registerListeners(PluginManager pluginManager) {
        pluginManager.registerEvents(new LobbyListeners(), this);
        pluginManager.registerEvents(new GameListeners(), this);
        pluginManager.registerEvents(new LaserWeapon(), this);
    }

    public static LaserTag getPlugin() {
        return plugin;
    }

    public StateManager getStateManager() {
        return stateManager;
    }

}
