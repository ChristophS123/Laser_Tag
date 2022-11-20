package de.christoph.lasertag.state.lobby;

import de.christoph.lasertag.Constants;
import de.christoph.lasertag.LaserTag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LobbyListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(LaserTag.gamePlayers.size() < Constants.MAY_PLAYERS) {
            LaserTag.gamePlayers.put(event.getPlayer(), 0);
            LaserTag.getPlugin().getStateManager().getLobbyState().checkForCountdownStart();
        } else {
            LaserTag.spectator.add(event.getPlayer());
        }
    }

}
