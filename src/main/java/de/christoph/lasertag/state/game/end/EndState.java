package de.christoph.lasertag.state.game.end;

import de.christoph.lasertag.LaserTag;
import de.christoph.lasertag.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class EndState {

    private EndCountdown endCountdown = null;

    public EndState() {
        for(Player all : Bukkit.getOnlinePlayers())
            all.teleport(LocationUtil.getLocation("lobbyspawn", LaserTag.getPlugin()));
        endCountdown = new EndCountdown();
        showResult();
    }

    private void showResult() {
        for(Player all : Bukkit.getOnlinePlayers()) {
            all.playSound(all.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 3);
            all.sendMessage("");
            all.sendMessage("§0----------");
            all.sendMessage("");
            sortGamePlayers().forEach((player, integer) -> {
                all.sendMessage("§6" + player.getName() + "§0| §cKills: " + integer);
            });
            all.sendMessage("");
            all.sendMessage("§0----------");
            all.sendMessage("");
        }
    }

    private Map<Player, Integer> sortGamePlayers() {
        return LaserTag.gamePlayers.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {throw new AssertionError();},
                        LinkedHashMap::new
                ));
    }

    public EndCountdown getEndCountdown() {
        return endCountdown;
    }

}
