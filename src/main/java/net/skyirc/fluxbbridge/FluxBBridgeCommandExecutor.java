/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skyirc.fluxbbridge;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
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
    		} else {
    			Player player = (Player) sender;
                        if (args.length > 2) {
                            
                        }
                }
    		return true;
    	}
    	return false;
    }
    
}