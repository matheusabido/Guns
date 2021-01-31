package br.com.abidux.guns.models;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import br.com.abidux.guns.Scheduler;
import net.minecraft.server.v1_8_R3.EnumParticle;

public class Shoot {
	
	private ArmorStand stand;
	private Location[] trajectory;
	private Player shooter;
	public int index = 0;
	
	private Gun gun;
	
	public Shoot(Gun gun, Player player) {
		this.gun = gun;
		this.shooter = player;
		this.trajectory = new Location[gun.range];
		Location location = player.getLocation().add(0, 1.5, 0);
		this.stand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
		this.stand.setGravity(false);
		this.stand.setVisible(false);
		for (int i = 0; i < gun.range; i++)
			trajectory[i] = location.clone().add(location.getDirection().multiply((i + 1) * 1.5f));
		Scheduler.shoots.add(this);
	}
	
	public Location[] getTrajectory() {
		return trajectory;
	}
	
	public Gun getGun() {
		return gun;
	}
	
	public ArmorStand getStand() {
		return stand;
	}
	
	public EnumParticle getParticle() {
		return gun.particle;
	}
	
	public Player getShooter() {
		return shooter;
	}
	
	public double getDamage() {
		return gun.damage;
	}
	
}