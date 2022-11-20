package de.christoph.lasertag;

import de.christoph.lasertag.state.StateManager;
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
    }

    private void registerListeners(PluginManager pluginManager) {
        pluginManager.registerEvents(new LobbyListeners(), this);
    }

    public static LaserTag getPlugin() {
        return plugin;
    }

    public StateManager getStateManager() {
        return stateManager;
    }

}
