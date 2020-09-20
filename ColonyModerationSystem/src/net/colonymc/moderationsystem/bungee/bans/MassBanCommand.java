package net.colonymc.moderationsystem.bungee.bans;

import net.colonymc.moderationsystem.Messages;
import net.colonymc.moderationsystem.bungee.SpigotConnector;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class MassBanCommand extends Command{

	public MassBanCommand() {
		super("massban");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(p.hasPermission("*")) {
				SpigotConnector.openMassBanMenu(p.getServer().getInfo(), p.getName());
			}
			else {
				p.sendMessage(new TextComponent(Messages.noPerm));
			}
		}
		else {
			sender.sendMessage(new TextComponent(Messages.onlyPlayers));
		}
	}

}
