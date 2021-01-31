package br.com.abidux.guns.models;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_8_R3.EnumParticle;

public class Gun {
	
	protected ItemStack item;
	
	protected String name;
	protected List<String> lore;
	protected Material material;
	protected EnumParticle particle;
	protected String commandName;
	protected int range;
	protected double damage;
	public Gun(String name, String[] lore, Material material, EnumParticle particle, String commandName, int range, double damage) {
		this.name = name;
		this.material = material;
		this.particle = particle;
		this.commandName = commandName;
		this.range = range;
		this.lore = Arrays.asList(lore);
		this.damage = damage;
		buildItem();
	}
	
	public void shoot(Player player) {
		new Shoot(this, player);
	}
	
	private void buildItem() {
		item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(lore);
		item.setItemMeta(meta);
	}
	
	public boolean is(ItemStack item) {
		return item.getType().equals(material) && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().hasLore() && item.getItemMeta().getDisplayName().equals(name) && item.getItemMeta().getLore().equals(lore);
	}
	
	public String getCommandName() {
		return commandName;
	}
	
	public ItemStack getItem() {
		return item;
	}
	
}