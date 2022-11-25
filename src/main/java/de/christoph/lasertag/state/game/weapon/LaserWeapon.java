package de.christoph.lasertag.state.game.weapon;

import de.christoph.lasertag.Constants;
import de.christoph.lasertag.LaserTag;
import de.christoph.lasertag.utils.ItemBuilder;
import de.christoph.lasertag.utils.LocationUtil;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class LaserWeapon implements Listener {

    public static void getLaserWeapon(Player player) {
        player.getInventory().setItem(1, new ItemBuilder(Material.IRON_HOE)
                .setDisplayName(Constants.LASER_WEAPON_NAME)
                .build());
    }

    private void shoot(Player player) {
        Snowball projectile = player.launchProjectile(Snowball.class);
        projectile.setCustomName(Constants.PROJECTILE_CUSTOM_NAME);
        projectile.setVelocity(projectile.getVelocity().multiply(3F));
        projectile.setCustomNameVisible(false);
        for(Player all : Bukkit.getOnlinePlayers())
            all.playSound(player.getLocation(), Sound.EXPLODE, 1, 3);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getPlayer().getItemInHand() == null || event.getPlayer().getItemInHand().getType().equals(Material.AIR))
            return;
        if(!event.getPlayer().getItemInHand().hasItemMeta() || !event.getPlayer().getItemInHand().getItemMeta().hasDisplayName())
            return;
        if(!event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(Constants.LASER_WEAPON_NAME))
            return;
        shoot(event.getPlayer());
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        System.out.println("test");
        if(event.getCause() != EntityDamageEvent.DamageCause.PROJECTILE)
            return;
        System.out.println("test1");
        if(!(event.getDamager() instanceof Projectile) || !(event.getEntity() instanceof Player))
            return;
        Projectile projectile = (Projectile) event.getDamager();
        if(!projectile.getCustomName().equals(Constants.PROJECTILE_CUSTOM_NAME))
            return;
        if(!(projectile.getShooter() instanceof Player))
            return;
        Player shooter = (Player) projectile.getShooter();
        Player shootedPlayer = (Player) event.getEntity();
        shootedPlayer.teleport(LocationUtil.getLocation("gamespawn", LaserTag.getPlugin()));
        shootedPlayer.sendMessage(Constants.PREFIX + Constants.GOT_KILLED.replace("%player%", shooter.getName()));
        shooter.sendMessage(Constants.PREFIX + Constants.KILLED_PLAYER.replace("%player%", shootedPlayer.getName()));
        LaserTag.gamePlayers.put(shooter, LaserTag.gamePlayers.get(shooter) + 1);
    }

}
