package de.christoph.lasertag.state.game;

import de.christoph.lasertag.LaserTag;
import de.christoph.lasertag.scoreboard.LaserTagBoard;
import de.christoph.lasertag.state.State;
import de.christoph.lasertag.state.game.weapon.LaserWeapon;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class GameListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(!LaserTag.getPlugin().getStateManager().getCurrentState().equals(State.GAME))
            return;
        event.setJoinMessage("");
        if(LaserTag.getPlugin().getStateManager().getGameState().getProtectionCountdown() != null)
            setProtectionBoard(event.getPlayer());
        else
            setGameBoard(event.getPlayer());
        event.getPlayer().setGameMode(GameMode.SPECTATOR);
        LaserTag.spectator.add(event.getPlayer());
    }

    private void setGameBoard(Player player) {
        LaserTagBoard.setGameBoard(player, LaserTag.getPlugin().getStateManager().getGameState().getGameCountdown().getTime());
    }

    private void setProtectionBoard(Player player) {
        LaserTagBoard.setProtectionBoard(player, LaserTag.getPlugin().getStateManager().getGameState().getProtectionCountdown().getTime());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if(!LaserTag.getPlugin().getStateManager().getCurrentState().equals(State.GAME))
            return;
        event.setQuitMessage("");
        if(LaserTag.spectator.contains(event.getPlayer()))
            return;
        if(!LaserTag.gamePlayers.containsKey(event.getPlayer()))
            return;
        LaserTag.gamePlayers.remove(event.getPlayer());
        LaserTag.getPlugin().getStateManager().getGameState().checkForEarlyEnd();
    }

}
