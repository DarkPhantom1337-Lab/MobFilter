/*
 * @Author DarkPhantom1337
 * @Version 1.0.0
 */
package ua.darkphantom1337.mobfilter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigFile {

	private FileConfiguration filecfg;
	private Main plugin;
	private File file;

	public ConfigFile(Main plugin) {
		this.plugin = plugin;
		setupCfgFile();
		if (getCfgFile().isSet("MobFilter"))
			saveCfgFile();
		else
			firstFill();
	}

	private void setupCfgFile() {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		file = new File(plugin.getDataFolder(), "config.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException localIOException) {
			}
		}
		new YamlConfiguration();
		filecfg = YamlConfiguration.loadConfiguration(file);
	}

	private FileConfiguration getCfgFile() {
		return filecfg;
	}

	public void saveCfgFile() {
		try {
			filecfg.save(file);
		} catch (IOException localIOException) {
		}
	}

	public void reloadCfgFile() {
		new YamlConfiguration();
		filecfg = YamlConfiguration.loadConfiguration(file);
	}

	private void firstFill() {
		getCfgFile().set("MobFilter", " File: config.yml || Author: DarkPhantom1337");
		getCfgFile().set("EnableBlockSpawn", true);
		getCfgFile().set("AllowedMobs", Arrays.asList(new String[] {"ZOMBIE"}));
		saveCfgFile();
	}

	public List<String> getAllowedMobs() {
		return getCfgFile().getStringList("AllowedMobs");
	}

	public Boolean isEnable() {
		return getCfgFile().getBoolean("EnableBlockSpawn");
	}

}
