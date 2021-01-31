package br.com.abidux.guns;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import br.com.abidux.guns.models.Shoot;
import br.com.abidux.guns.utils.Utils;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class Scheduler {
	
	public static List<Shoot> shoots = new ArrayList<>();
	private static List<Shoot> remove = new ArrayList<>();
	
	static {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
			@Override
			public void run() {
				remove.clear();
				for (Shoot shoot : shoots) {
					if (shoot.index < shoot.getTrajectory().length) {
						Location location = shoot.getTrajectory()[shoot.index++];
						PacketPlayOutWorldParticles packet = Utils.spawnParticle(shoot.getParticle(), location, 0, 0, 0, 0, 2);
						for (Player player : Bukkit.getOnlinePlayers())
							((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
						shoot.getStand().teleport(location);
						List<Entity> entityList = shoot.getStand().getNearbyEntities(0.5, 0.5, 0.5);
						for (Entity entity : entityList) {
							if (!(entity instanceof LivingEntity)) continue;
							if (!(entity instanceof Player && shoot.getShooter().equals((Player) entity)) && !(entity instanceof ArmorStand)) {
								((LivingEntity) entity).damage(shoot.getDamage());
								remove.add(shoot);
								break;
							}
						}
						if (!location.getBlock().getType().equals(Material.AIR)) remove.add(shoot);
					}else remove.add(shoot);
				}
				for (Shoot shoot : remove) {
					shoot.getStand().remove();
					shoots.remove(shoot);
				}
			}
		}, 0, 1);
	}
	
}