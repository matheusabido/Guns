package br.com.abidux.guns.utils;

import org.bukkit.Location;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class Utils {
	
	public static PacketPlayOutWorldParticles spawnParticle(EnumParticle particle, Location location, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
		return spawnParticle(particle, location.getX(), location.getY(), location.getZ(), offsetX, offsetY, offsetZ, speed, amount);
	}
	
	public static PacketPlayOutWorldParticles spawnParticle(EnumParticle particle, double x, double y, double z, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
		return new PacketPlayOutWorldParticles(particle, true, (float) x, (float) y, (float) z, offsetX, offsetY, offsetZ, speed, amount, null);
	}
	
}