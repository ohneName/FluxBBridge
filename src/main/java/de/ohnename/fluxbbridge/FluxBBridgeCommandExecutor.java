package de.ohnename.fluxbbridge;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Florian Reichmuth
 */
public class FluxBBridgeCommandExecutor implements CommandExecutor {
	private FluxBBridge plugin;

	FluxBBridgeCommandExecutor(FluxBBridge aThis) {
		this.plugin = aThis;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("forum")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			}
			else {
				Player player = (Player) sender;

				if(args.length == 0) {
					player.sendMessage(ChatColor.RED + "Bitte gib einen Forenaccount an.");
				}

				else if(args.length == 1) {

					String keyLink;

					try {
						keyLink = Tools.getKeyLink(args[0], plugin);
					}
					catch (Tools.BoardUserNotExistingException e) {
						player.sendMessage(ChatColor.RED + "Dieser Forenaccount existiert nicht.");
						return true;
					}

					if(keyLink == null) {
						player.sendMessage(ChatColor.RED + "Bei der Verarbeitung ist ein Fehler aufgetreten.");
						return true;
					}

					player.sendMessage(ChatColor.GRAY + "Deinen Schlüssel findest du hier:");
					player.sendMessage(ChatColor.GOLD + keyLink);

				}
				else if(args.length >= 2) {

					boolean check;

					try {
						check = Tools.check(player.getName(), args[0], args[1], plugin);
					}
					catch (Tools.BoardUserNotExistingException e) {
						player.sendMessage(ChatColor.RED + "Dieser Forenaccount existiert nicht.");
						return true;
					}
					catch (Tools.PasswordWrongException e) {
						player.sendMessage(ChatColor.RED + "Dieser Schlüssel ist ungültig.");
						return true;
					}
					catch (Tools.MinecraftAccountTakenException e) {
						player.sendMessage(ChatColor.RED + "Dein Minecraftaccount ist bereits einem anderen Forenaccount zugeordnet.");
						return true;
					}

					if(!check) {
						player.sendMessage(ChatColor.RED + "Bei der Verarbeitung ist ein Fehler aufgetreten.");
						return true;
					}

					player.sendMessage(ChatColor.GRAY + "Deinem Forenaccount " + ChatColor.GOLD + args[0] + ChatColor.GRAY + " wurde dein Minecraftaccount " + ChatColor.GOLD + player.getName() + ChatColor.GRAY + " erfolgreich zugeordnet.");

				}
			}
			return true;

		}

		return false;

	}

}
