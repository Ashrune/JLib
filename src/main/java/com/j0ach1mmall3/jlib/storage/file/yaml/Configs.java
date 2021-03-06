package com.j0ach1mmall3.jlib.storage.file.yaml;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since Unknown
 * @deprecated {@link ConfigLoader}
 */
@Deprecated
public final class Configs {

    /**
     * Let nobody instantiate this class
     */
    private Configs() {
    }

    /**
     * Deprecated, use {@link ConfigLoader} instead
     * @param name Deprecated, use {@link ConfigLoader} instead
     * @param plugin Deprecated, use {@link ConfigLoader} instead
     * @return Deprecated, use {@link ConfigLoader} instead
     */
    public static FileConfiguration getConfig(String name, Plugin plugin) {
        File configfile = new File(plugin.getDataFolder(), name);
        return YamlConfiguration.loadConfiguration(configfile);
    }

    /**
     * Deprecated, use {@link ConfigLoader} instead
     * @param name Deprecated, use {@link ConfigLoader} instead
     * @param plugin Deprecated, use {@link ConfigLoader} instead
     */
    @SuppressWarnings("deprecation")
    public static void saveConfig(String name, Plugin plugin) {
        File configfile = new File(plugin.getDataFolder(), name);
        try {
            getConfig(name, plugin).save(configfile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Deprecated, use {@link ConfigLoader} instead
     * @param name Deprecated, use {@link ConfigLoader} instead
     * @param plugin Deprecated, use {@link ConfigLoader} instead
     */
    public static void saveDefaultConfig(String name, Plugin plugin) {
        File configfile = new File(plugin.getDataFolder(), name);
        if (!configfile.exists()) {
             plugin.saveResource(name, false);
        }
    }

    /**
     * Deprecated, use {@link ConfigLoader} instead
     * @param name Deprecated, use {@link ConfigLoader} instead
     * @param plugin Deprecated, use {@link ConfigLoader} instead
     */
    @SuppressWarnings("deprecation")
    public static void reloadConfig(String name, Plugin plugin) {
        File configfile = new File(plugin.getDataFolder(), name);
        FileConfiguration config = YamlConfiguration.loadConfiguration(configfile);

        InputStream stream = plugin.getResource(name);
        if (stream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(stream);
            config.setDefaults(defConfig);
        }
    }
}
