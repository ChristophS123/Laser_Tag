package de.christoph.lasertag.state.game;

import de.christoph.lasertag.Constants;
import de.christoph.lasertag.LaserTag;
import de.christoph.lasertag.scoreboard.LaserTagBoard;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ProtectionCountdown {

    private int time = Constants.PROTECTION_TIME;
    private int taskID;

    public ProtectionCountdown() {
        start();
    }

    private void start() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(LaserTag.getPlugin(), () -> {
            switch (time) {
                case Constants.PROTECTION_TIME:
                case 15:
                case 5:
                case 3:
                case 2:
                case 1:
                    for(Player gamePlayer : Bukkit.getOnlinePlayers()) {
                        gamePlayer.sendMessage(Constants.PREFIX + Constants.PROTECTION_TIME_END.replace("%time%", "" + time));
                        gamePlayer.playSound(gamePlayer.getLocation(), Sound.NOTE_PLING, 1, 3);
                    }
                    break;
                case 0:
                    LaserTag.getPlugin().getStateManager().getGameState().endProtectionTime();
                    break;
                default:
                    break;
            }
            for (Player all : Bukkit.getOnlinePlayers()) {
                LaserTagBoard.setProtectionBoard(all, time);
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
