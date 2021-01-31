package br.com.abidux.guns;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.abidux.guns.commands.GetGun;
import br.com.abidux.guns.events.RightClick;
import br.com.abidux.guns.models.Gun;
import net.minecraft.server.v1_8_R3.EnumParticle;

public class Main extends JavaPlugin {
	
	public static Main plugin;
	public static Gun[] guns = new Gun[] {
		new Gun("Wood Gun", new String[] {"lore1", "lore2"}, Material.WOOD_HOE, EnumParticle.CRIT, "woodgun", 32, 10),
		new Gun("Fera", new String[] {"§6§oPoderosa!"}, Material.GOLD_HOE, EnumParticle.FLAME, "fera", 32, 20)
	};
	
	@Override
	public void onEnable() {
		plugin = this;
		new Scheduler();
		getCommand("getgun").setExecutor(new GetGun());
		Bukkit.getPluginManager().registerEvents(new RightClick(), this);
	}
	
}