package de.christoph.lasertag.state.lobby;

import de.christoph.lasertag.Constants;
import de.christoph.lasertag.LaserTag;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LobbyState {

    private LobbyCountdown lobbyCountdown = null;

    public void checkForCountdownStart() {
        if(LaserTag.gamePlayers.size() >= Constants.MIN_PLAYERS) {
            lobbyCountdown = new LobbyCountdown();
        }
    }

    public void checkForCountdownStop() {
        if(lobbyCountdown == null)
            return;
        if(LaserTag.gamePlayers.size() <= Constants.MIN_PLAYERS) {
            cancelLobbyCountdown();
            lobbyCountdown = null;
        }
    }

    private void cancelLobbyCountdown() {
        Bukkit.getScheduler().cancelTask(lobbyCountdown.getTaskID());
        for(Player all : Bukkit.getOnlinePlayers())
            all.setLevel(0);
    }

    public void closeLobbyState() {
        cancelLobbyCountdown();
        System.out.println("State Changed");
        //TODO: Start Game State
    }

}
