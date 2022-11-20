package de.christoph.lasertag.state.game;

import de.christoph.lasertag.Constants;
import de.christoph.lasertag.LaserTag;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class GameCountdown {

    private int taskID;
    private int time = Constants.GAME_TIME;

    public GameCountdown() {
        start();
    }

    private void start() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(LaserTag.getPlugin(), () -> {
            switch (time) {
                case Constants.GAME_TIME:
                case 30:
                case 15:
                case 10:
                case 5:
                case 3:
                case 2:
                case 1:
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        all.sendMessage(Constants.PREFIX + Constants.GAME_COUNTDOWN_MESSAGE.replace("%time%", "" + time));
                        all.playSound(all.getLocation(), Sound.NOTE_PLING, 1, 3);
                    }
                    break;
                case 0:
                    LaserTag.getPlugin().getStateManager().getGameState().stopGame();
                    break;
                default:
                    break;
            }
            time--;
        }, 0, 20);
    }

    public int getTaskID() {
        return taskID;
    }

}
