/*
 * @Author DarkPhantom1337
 * @Version 1.0.0
 */
package ua.darkphantom1337.mobfilter;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	private ConfigFile cfg;

	public void onEnable() {
		try {
			cfg = new ConfigFile(this);
			Bukkit.getPluginManager().registerEvents(this, this);
			Bukkit.getConsoleSender()
					.sendMessage("§a[§eMobFilter§a] §f-> §aPlugin succesfully enabled! // by Darkphantom1337, 2020");
		} catch (Exception e) {
			Bukkit.getConsoleSender()
					.sendMessage("§c[§eMobFilter§c] §f-> §cError in enabling plugin! Plugin disabled!\nError:"
							+ e.getLocalizedMessage());
			this.setEnabled(false);
		}
	}

	public void onDisable() {
		Bukkit.getConsoleSender()
				.sendMessage("§c[§eMobFilter§c] §f-> §cPlugin succesfully disabled! // by Darkphantom1337, 2020");
	}

	@EventHandler
	public void onBucketEmpty(CreatureSpawnEvent e) {
		if (cfg.isEnable())
			if (!cfg.getAllowedMobs().contains(e.getEntityType().name()))
				e.setCancelled(true);
	}

}
