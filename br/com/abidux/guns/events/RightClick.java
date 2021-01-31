package br.com.abidux.guns.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import br.com.abidux.guns.Main;
import br.com.abidux.guns.models.Gun;

public class RightClick implements Listener {
	
	@EventHandler
	void rightclick(PlayerInteractEvent e) {
		ItemStack item = e.getPlayer().getItemInHand();
		if (item == null) return;
		Gun gun = null;
		for (Gun g : Main.guns) {
			if (g.is(item)) {
				gun = g;
				break;
			}
		}
		if (gun != null) e.setCancelled(true);
		if (e.getAction() == Action.RIGHT_CLICK_AIR) {
			if (gun != null)
				gun.shoot(e.getPlayer());
		}
	}
	
}