package de.ohnename.fluxbbridge;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Florian Reichmuth
 */
public class FluxBBridge extends JavaPlugin {
	@Override
	public void onEnable() {
		getLogger().info("FluxBBridge has been enabled");
		getCommand("forum").setExecutor(new FluxBBridgeCommandExecutor(this));
		this.saveDefaultConfig();
	}

	@Override
	public void onDisable() {
		getLogger().info("FluxBBridge has been disabled");
	}

}
