package de.christoph.lasertag.state.game;

import de.christoph.lasertag.Constants;
import de.christoph.lasertag.LaserTag;
import de.christoph.lasertag.state.end.EndState;
import de.christoph.lasertag.state.game.weapon.LaserWeapon;
import de.christoph.lasertag.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class GameState {

    private ProtectionCountdown protectionCountdown = null;
    private GameCountdown gameCountdown = null;

    public GameState() {
        startGame();
    }

    private void startGame() {
        for(Player all : Bukkit.getOnlinePlayers()) {
            if(LaserTag.getPlugin().getConfig().contains("gamespawn.World")) {
                all.teleport(LocationUtil.getLocation("gamespawn", LaserTag.getPlugin()));
                LaserWeapon.getLaserWeapon(all);
            } else
                Bukkit.getConsoleSender().sendMessage(Constants.PREFIX + Constants.SETUP_NOT_FINISHED);
        }
        for(Player spectator : LaserTag.spectator)
            spectator.setGameMode(GameMode.SPECTATOR);
        protectionCountdown = new ProtectionCountdown();
    }

    public void endProtectionTime() {
        Bukkit.getScheduler().cancelTask(protectionCountdown.getTaskID());
        protectionCountdown = null;
        for(Player all : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < 150; i++)
                all.sendMessage("");
            all.sendMessage(Constants.GAME_START);
            all.playSound(all.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 3);
        }
        gameCountdown = new GameCountdown();
    }

    public void checkForEarlyEnd() {
        if(LaserTag.gamePlayers.size() < Constants.MIN_PLAYERS) {
            stopGame();
        }
    }

    public void stopGame() {
        stopCountdown();
        EndState endState = new EndState();
        LaserTag.getPlugin().getStateManager().setEndState(endState);
    }

    private void stopCountdown() {
        Bukkit.getScheduler().cancelTask(LaserTag.getPlugin().getStateManager().getGameState().gameCountdown.getTaskID());
    }

    public ProtectionCountdown getProtectionCountdown() {
        return protectionCountdown;
    }

    public GameCountdown getGameCountdown() {
        return gameCountdown;
    }

}
