package de.christoph.lasertag.state.lobby;

import de.christoph.lasertag.Constants;
import de.christoph.lasertag.LaserTag;
import de.christoph.lasertag.state.game.GameState;
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
        GameState gameState = new GameState();
        LaserTag.getPlugin().getStateManager().setGameState(gameState);
    }

    public LobbyCountdown getLobbyCountdown() {
        return lobbyCountdown;
    }

}
