package de.christoph.lasertag.state.game;

import de.christoph.lasertag.LaserTag;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GameListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage("");
        event.getPlayer().setGameMode(GameMode.SPECTATOR);
        LaserTag.spectator.add(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage("");
        if(LaserTag.spectator.contains(event.getPlayer()))
            return;
        if(!LaserTag.gamePlayers.containsKey(event.getPlayer()))
            return;
        LaserTag.gamePlayers.remove(event.getPlayer());
        LaserTag.getPlugin().getStateManager().getGameState().checkForEarlyEnd();
    }

}
