package net.skyirc.fluxbbridge;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin; 
/**
 * Hello world!
 *
 */
public class FluxBBridge extends JavaPlugin 
{
    @Override
    public void onEnable(){
        getLogger().info("FluxBBridge has been enabled");
        getCommand("basic").setExecutor(new FluxBBridgeCommandExecutor(this));
        this.saveDefaultConfig();
    }
 
    @Override
    public void onDisable() {
        getLogger().info("FluxBBridge has been disabled");
    }
        
}
