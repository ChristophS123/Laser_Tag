package de.christoph.lasertag.scoreboard;

import de.christoph.lasertag.Constants;
import de.christoph.lasertag.LaserTag;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class LaserTagBoard {

    public static void setLobbyBoard(Player player, int time) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("123", "123");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(Constants.SCOREBOARD_TITLE);
        objective.getScore(" ").setScore(4);
        objective.getScore("§a§lLobby State:").setScore(3);
        objective.getScore("§6 > " + time).setScore(2);
        objective.getScore("  ").setScore(1);
        player.setScoreboard(scoreboard);
    }

    public static void setProtectionBoard(Player player, int time) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("123", "123");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(Constants.SCOREBOARD_TITLE);
        objective.getScore(" ").setScore(4);
        objective.getScore("§a§lProtectionTime:").setScore(3);
        objective.getScore("§6 > " + time).setScore(2);
        objective.getScore("  ").setScore(1);
        player.setScoreboard(scoreboard);
    }

    public static void setGameBoard(Player player, int time) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("123", "123");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(Constants.SCOREBOARD_TITLE);
        objective.getScore(" ").setScore(7);
        objective.getScore("§a§lGame:").setScore(6);
        objective.getScore("§6 > " + time).setScore(5);
        objective.getScore("  ").setScore(4);
        if(LaserTag.gamePlayers.containsKey(player)) {
            objective.getScore("§a§lKills:").setScore(3);
            objective.getScore("§6 > " + LaserTag.gamePlayers.get(player)).setScore(2);
            objective.getScore("   ").setScore(1);
        }
        player.setScoreboard(scoreboard);
    }

    public static void setEndBoard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("123", "123");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(Constants.SCOREBOARD_TITLE);
        objective.getScore("  ").setScore(4);
        if(LaserTag.gamePlayers.containsKey(player)) {
            objective.getScore("§a§lKills:").setScore(3);
            objective.getScore("§6 > " + LaserTag.gamePlayers.get(player)).setScore(2);
            objective.getScore("   ").setScore(1);
        } else {
            objective.getScore("§a§lYou are an spectator").setScore(3);
            objective.getScore("§6 > no kills" + LaserTag.gamePlayers.get(player)).setScore(2);
            objective.getScore("   ").setScore(1);
        }
        player.setScoreboard(scoreboard);
    }

}
