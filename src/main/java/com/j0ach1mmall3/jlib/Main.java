package com.j0ach1mmall3.jlib;

import com.j0ach1mmall3.jlib.integration.UpdateChecker;
import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.minigameapi.MinigameAPI;
import com.j0ach1mmall3.jlib.integration.vault.ChatHook;
import com.j0ach1mmall3.jlib.integration.vault.EconomyHook;
import com.j0ach1mmall3.jlib.integration.vault.PermissionHook;
import com.j0ach1mmall3.jlib.integration.vault.VaultHook;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since Unknown
 */
public class Main extends JavaPlugin{
    private boolean placeholderAPI;
    private MinigameAPI api;
	public void onEnable(){
        UpdateChecker checker = new UpdateChecker(6603, this.getDescription().getVersion());
        if (checker.checkUpdate()) {
            General.sendColoredMessage(this, "A new update is available!", ChatColor.GOLD);
            General.sendColoredMessage(this, "Version " + checker.getVersion() + " (Current: " + this.getDescription().getVersion() + ")", ChatColor.GOLD);
        } else {
            General.sendColoredMessage(this, "You are up to date!", ChatColor.GREEN);
        }
		if(Bukkit.getPluginManager().getPlugin("Vault") != null){
            VaultHook hook = new PermissionHook();
			if(hook.isRegistered()){
	            General.sendColoredMessage(this, "Successfully hooked into Vault Permissions for extended functionality", ChatColor.GREEN);
	        } else {
	        	General.sendColoredMessage(this, "No Vault Permission Registration found, some placeholders may not work!", ChatColor.GOLD);
	        }
            hook = new ChatHook();
			if(hook.isRegistered()){
	            General.sendColoredMessage(this, "Successfully hooked into Vault Chat for extended functionality", ChatColor.GREEN);
	        } else {
	        	General.sendColoredMessage(this, "No Vault Chat Registration found, some placeholders may not work!", ChatColor.GOLD);
	        }
            hook = new EconomyHook();
			if(hook.isRegistered()){
	            General.sendColoredMessage(this, "Successfully hooked into Vault Economy for extended functionality", ChatColor.GREEN);
	        } else {
	        	General.sendColoredMessage(this, "No Vault Economy Registration found, some placeholders may not work!", ChatColor.GOLD);
	        }
		} else {
			General.sendColoredMessage(this, "Vault not found, some placeholders may not work!", ChatColor.RED);
		}
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            this.placeholderAPI = true;
            General.sendColoredMessage(this, "Successfully hooked into PlaceholderAPI for more Placeholders", ChatColor.GREEN);
        } else {
            this.placeholderAPI = false;
            General.sendColoredMessage(this, "PlaceholderAPI not found, switching over to default Placeholders", ChatColor.GOLD);
        }
        this.api = new MinigameAPI(this);
        new JoinListener(this);
	}

    /**
     * Returns if PlaceholderAPI is found
     * @return If PlaceholderAPI is found
     */
    public boolean isPlaceholderAPI() {
        return placeholderAPI;
    }

    /**
     * Returns the MinigameAPI instance
     * @return The MinigameAPI instance
     */
    public MinigameAPI getApi() {
        return this.api;
    }
}
