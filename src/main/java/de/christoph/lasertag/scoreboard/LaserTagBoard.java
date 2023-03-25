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
        objective.getScore(Constants.LOBBY_STATE_SCOREBOARD).setScore(3);
        if(LaserTag.gamePlayers.size() >= Constants.MIN_PLAYERS)
            objective.getScore("§6 > " + time).setScore(2);
        else
            objective.getScore(Constants.WAITING_FOR_PLAYERS_SCOREBOARD).setScore(2);
        objective.getScore("  ").setScore(1);
        player.setScoreboard(scoreboard);
    }

    public static void setProtectionBoard(Player player, int time) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("123", "123");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(Constants.SCOREBOARD_TITLE);
        objective.getScore(" ").setScore(4);
        objective.getScore(Constants.PROTECTION_TIME_SCOREBOARD).setScore(3);
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
        objective.getScore(Constants.GAME_SCOREBOARD).setScore(6);
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
            objective.getScore(Constants.KILLS_SCOREBOARD).setScore(3);
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
