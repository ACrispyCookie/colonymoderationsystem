package net.colonymc.moderationsystem.bungee.reports;

import net.colonymc.moderationsystem.Messages;
import net.colonymc.moderationsystem.bungee.SpigotConnector;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ReportCommand extends Command {

	public ReportCommand() {
		super("report");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 1) {
				if(ProxyServer.getInstance().getPlayer(args[0]) != null) {
					if(!ProxyServer.getInstance().getPlayer(args[0]).equals(p)) {
						if(Report.getByPlayer(ProxyServer.getInstance().getPlayer(args[0])).size() < 3) {
							boolean hasAlreadyReported = false;
							for(Report r : Report.getByPlayer(ProxyServer.getInstance().getPlayer(args[0]))) {
								if(r.getReporterUuid().equals(p.getUniqueId().toString())) {
									hasAlreadyReported = true;
									break;
								}
							}
							if(!hasAlreadyReported) {
								SpigotConnector.openReportMenu(p.getServer().getInfo(), p.getName(), ProxyServer.getInstance().getPlayer(args[0]).getName());
							}
							else {
								p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', " &5&l� &cYou have already reported this player!")));
							}
						}
						else {
							p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', " &5&l� &cThis player has already been reported too many times!")));
						}
					}
					else {
						p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', " &5&l� &cYou cannot report yourself!")));
					}
				}
				else {
					p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', " &5&l� &cThis player is not online!")));
				}
			}
			else {
				p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', " &5&l� &fUsage: &d/report <player>")));
			}
		}
		else {
			sender.sendMessage(new TextComponent(Messages.onlyPlayers));
		}
	}

}
