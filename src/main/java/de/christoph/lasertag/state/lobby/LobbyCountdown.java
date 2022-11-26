package de.christoph.lasertag.state.lobby;

import de.christoph.lasertag.Constants;
import de.christoph.lasertag.LaserTag;
import de.christoph.lasertag.scoreboard.LaserTagBoard;
import de.christoph.lasertag.state.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class LobbyCountdown {

    private int taskID;
    private int time = Constants.LOBBY_COUNTDOWN_SECONDS;

    public LobbyCountdown() {
        startCountdown();
    }

    private void startCountdown() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(LaserTag.getPlugin(), () -> {
            switch (time) {
                case Constants.LOBBY_COUNTDOWN_SECONDS:
                case 30:
                case 5:
                case 3:
                case 2:
                case 1:
                    for(Player gamePlayer : Bukkit.getOnlinePlayers()) {
                        gamePlayer.sendMessage(Constants.PREFIX + Constants.LOBBY_TIME_END.replace("%time%", "" + time));
                        gamePlayer.playSound(gamePlayer.getLocation(), Sound.NOTE_PLING, 1, 3);
                    }
                    break;
                case 0:
                    LaserTag.getPlugin().getStateManager().getLobbyState().closeLobbyState();
                    break;
                default:
                    break;
            }
            for(Player gamePlayer : Bukkit.getOnlinePlayers()) {
                gamePlayer.setLevel(time);
                LaserTagBoard.setLobbyBoard(gamePlayer, time);
            }
            time--;
        }, 0, 20);
    }

    public int getTaskID() {
        return taskID;
    }

    public int getTime() {
        return time;
    }

}
