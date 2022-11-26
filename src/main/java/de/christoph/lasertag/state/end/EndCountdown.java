package de.christoph.lasertag.state.end;

import de.christoph.lasertag.Constants;
import de.christoph.lasertag.LaserTag;
import de.christoph.lasertag.scoreboard.LaserTagBoard;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class EndCountdown {

    private int taskID;
    private int time = Constants.RESTART_TIME;

    public EndCountdown() {
        startCountdown();
    }

    private void startCountdown() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(LaserTag.getPlugin(), () -> {
            switch (time) {
                case Constants.RESTART_TIME:
                case 30:
                case 5:
                case 3:
                case 2:
                case 1:
                    for(Player gamePlayer : Bukkit.getOnlinePlayers()) {
                        gamePlayer.sendMessage(Constants.PREFIX + Constants.SERVER_RESTART_IN.replace("%time%", "" + time));
                        gamePlayer.playSound(gamePlayer.getLocation(), Sound.NOTE_PLING, 1, 3);
                    }
                    break;
                case 0:
                    //TODO: Stop Server
                    break;
                default:
                    break;
            }
            for(Player all : Bukkit.getOnlinePlayers()) {
                LaserTagBoard.setEndBoard(all);
            }
            time--;
        }, 0, 20);
    }

    public int getTaskID() {
        return taskID;
    }

}
