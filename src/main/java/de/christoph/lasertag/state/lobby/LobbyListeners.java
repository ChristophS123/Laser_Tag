package de.christoph.lasertag.state.lobby;

import de.christoph.lasertag.Constants;
import de.christoph.lasertag.LaserTag;
import de.christoph.lasertag.scoreboard.LaserTagBoard;
import de.christoph.lasertag.state.State;
import de.christoph.lasertag.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LobbyListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(!LaserTag.getPlugin().getStateManager().getCurrentState().equals(State.LOBBY))
            return;
        event.getPlayer().getInventory().clear();
        event.setJoinMessage(Constants.JOIN_MESSAGE.replace("%player%", event.getPlayer().getName()));
        setBoard(event.getPlayer());
        if(LaserTag.getPlugin().getConfig().contains("lobbyspawn.World")) {
            event.getPlayer().teleport(LocationUtil.getLocation("lobbyspawn", LaserTag.getPlugin()));
        } else
            Bukkit.getConsoleSender().sendMessage(Constants.PREFIX + Constants.SETUP_NOT_FINISHED);
        if(LaserTag.gamePlayers.size() < Constants.MAY_PLAYERS) {
            LaserTag.gamePlayers.put(event.getPlayer(), 0);
            LaserTag.getPlugin().getStateManager().getLobbyState().checkForCountdownStart();
        } else {
            LaserTag.spectator.add(event.getPlayer());
        }
    }

    private void setBoard(Player player) {
        if(LaserTag.getPlugin().getStateManager().getLobbyState().getLobbyCountdown() == null)
            LaserTagBoard.setLobbyBoard(player, Constants.LOBBY_COUNTDOWN_SECONDS);
        else {
            LaserTagBoard.setLobbyBoard(player, LaserTag.getPlugin()
                    .getStateManager()
                    .getLobbyState()
                    .getLobbyCountdown()
                    .getTime());
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if(!LaserTag.getPlugin().getStateManager().getCurrentState().equals(State.LOBBY))
            return;
        event.setQuitMessage(Constants.QUIT_MESSAGE.replace("%player%", event.getPlayer().getName()));
        if(LaserTag.spectator.contains(event.getPlayer())) {
            LaserTag.spectator.remove(event.getPlayer());
            return;
        }
        if(!LaserTag.gamePlayers.containsKey(event.getPlayer()))
            return;
        LaserTag.gamePlayers.remove(event.getPlayer());
        LaserTag.getPlugin().getStateManager().getLobbyState().checkForCountdownStop();
    }

}
