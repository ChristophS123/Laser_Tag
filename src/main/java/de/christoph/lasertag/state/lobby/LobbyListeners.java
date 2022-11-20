package de.christoph.lasertag.state.lobby;

import de.christoph.lasertag.Constants;
import de.christoph.lasertag.LaserTag;
import de.christoph.lasertag.state.State;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LobbyListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(!LaserTag.getPlugin().getStateManager().getCurrentState().equals(State.LOBBY))
            return;
        event.setJoinMessage(Constants.JOIN_MESSAGE.replace("%player%", event.getPlayer().getName()));
        if(LaserTag.gamePlayers.size() < Constants.MAY_PLAYERS) {
            LaserTag.gamePlayers.put(event.getPlayer(), 0);
            LaserTag.getPlugin().getStateManager().getLobbyState().checkForCountdownStart();
        } else {
            LaserTag.spectator.add(event.getPlayer());
        }
    }

}
