package de.christoph.lasertag.state.lobby;

import de.christoph.lasertag.Constants;
import de.christoph.lasertag.LaserTag;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LobbyState {

    private LobbyCountdown lobbyCountdown;

    public void checkForCountdownStart() {
        if(LaserTag.gamePlayers.size() >= Constants.MIN_PLAYERS) {
            lobbyCountdown = new LobbyCountdown();
        }
    }

    public void closeLobbyState() {
        Bukkit.getScheduler().cancelTask(lobbyCountdown.getTaskID());
        System.out.println("State Changed");
        //TODO: Start Game State
    }

}
