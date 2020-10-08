package net.colonymc.moderationsystem.spigot.queue;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.colonymc.moderationsystem.spigot.BungeecordConnector;
import net.colonymc.moderationsystem.spigot.Main.SERVER;

public class QueueCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			String servers = "";
			for(int i = 1; i < SERVER.values().length; i++) {
				if(i + 1 == SERVER.values().length) {
					servers = servers + SERVER.values()[i];
				}
				else {
					servers = servers + SERVER.values()[i] + ", ";
				}
			}
			Player p = (Player) sender;
			if(args.length == 1) {
				if(SERVER.contains(args[0].toUpperCase())) {
					BungeecordConnector.sendQueue(p, args[0]);
				}
				else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', " &5&l� &fPlease specify a valid server. (Available servers: &d" + servers + "&f)"));
				}
			}
			else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', " &5&l� &fUsage: &d/queue <server>\n &5&l� &fAvailable servers: &d" + servers));
			}
		}
		else {
			sender.sendMessage(" � Please use the bungeecord console to add players to the queue!");
		}
		return false;
	}

}
