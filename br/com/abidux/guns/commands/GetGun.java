package br.com.abidux.guns.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.abidux.guns.Main;
import br.com.abidux.guns.models.Gun;

public class GetGun implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("getgun")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length == 1) {
					String name = args[0];
					Gun gun = null;
					for (Gun g : Main.guns) {
						if (g.getCommandName().equalsIgnoreCase(name)) {
							gun = g;
							break;
						}
					}
					if (gun != null && player.getInventory().firstEmpty() != -1)
						player.getInventory().addItem(gun.getItem());
				}
			}
		}
		return false;
	}
	
}