package com.j0ach1mmall3.jlib.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 27/12/15
 */
public final class AnimatedGUI {
    private final List<GUI> guis;
    private final Player player;
    private final long interval;
    private final boolean repeat;
    private int count = 0;
    private int taskId;

    /**
     * Creates a new Animated GUI
     * @param guis The GUIs that make up this Animated GUI
     * @param player The Player this Animated GUI is intended for
     * @param interval The interval between the updates
     * @param repeat Whether the sequence should repeat itself after all the GUIs are shown
     */
    public AnimatedGUI(List<GUI> guis, Player player, long interval, boolean repeat) {
        this.guis = guis;
        this.player = player;
        this.interval = interval;
        this.repeat = repeat;
    }

    /**
     * Creates a new Animated GUI
     * @param guis The GUIs that make up this Animated GUI
     * @param player The Player this Animated GUI is intended for
     * @param interval The interval between the updates
     */
    public AnimatedGUI(List<GUI> guis, Player player, long interval) {
        this(guis, player, interval, false);
    }

    /**
     * Returns the list of GUIs that make up this Animated GUI
     * @return The list of GUIs
     */
    public List<GUI> getGuis() {
        return this.guis;
    }

    /**
     * Returns the Player this Animated GUI is intended for
     * @return the Player this Animated GUI is intended for
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Returns the interval between the updates
     * @return the interval between the updates
     */
    public long getInterval() {
        return this.interval;
    }

    /**
     * Returns whether the sequence should repeat itself after all the GUIs are shown
     * @return whether the sequence should repeat itself
     */
    public boolean isRepeat() {
        return this.repeat;
    }

    /**
     * Starts the task of showing the GUIs
     * @param plugin The plugin that is associated with this task
     */
    public void start(Plugin plugin) {
        this.taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                if(AnimatedGUI.this.count >= AnimatedGUI.this.guis.size()) {
                    if(AnimatedGUI.this.repeat) AnimatedGUI.this.count = 0;
                    else {
                        Bukkit.getScheduler().cancelTask(AnimatedGUI.this.taskId);
                        return;
                    }
                }
                int id = AnimatedGUI.this.count;
                AnimatedGUI.this.count++;
                if(AnimatedGUI.this.player.isOnline()) AnimatedGUI.this.guis.get(id).open(AnimatedGUI.this.player);
                else Bukkit.getScheduler().cancelTask(AnimatedGUI.this.taskId);
            }
        }, 0, this.interval);
    }

    /**
     * Stops the task of showing the GUIs
     */
    public void stop() {
        Bukkit.getScheduler().cancelTask(AnimatedGUI.this.taskId);
    }

    /**
     * Determines whether a player has legitimately clicked in this Animated GUI
     * @param event The InventoryClickEvent
     * @return Wether the player has clicked in the Animated GUI
     */
    public boolean hasClicked(InventoryClickEvent event) {
        for(GUI gui : this.guis) {
            if(gui.hasClicked(event)) return true;
        }
        return false;
    }
}
