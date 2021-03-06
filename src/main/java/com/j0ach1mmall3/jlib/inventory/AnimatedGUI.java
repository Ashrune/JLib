package com.j0ach1mmall3.jlib.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 27/12/15
 */
public final class AnimatedGUI {
    private final Player player;
    private final List<? extends GUI> guis;
    private final long interval;
    private final boolean repeat;
    private int taskId;
    private int count;

    /**
     * Constructs a new Animated GUI
     * @param player The Player associated with this Animated GUI
     * @param guis The GUIs that make up this Animated GUI
     * @param interval The interval between the updates
     * @param repeat Whether the sequence should repeat itself after all the GUIs are shown
     */
    public AnimatedGUI(Player player, List<? extends GUI> guis, long interval, boolean repeat) {
        this.player = player;
        this.guis = guis;
        this.interval = interval;
        this.repeat = repeat;
    }

    /**
     * Constructs a new Animated GUI
     * @param player The Player associated with this Animated GUI
     * @param guis The GUIs that make up this Animated GUI
     * @param interval The interval between the updates
     */
    public AnimatedGUI(Player player, List<? extends GUI> guis, long interval) {
        this(player, guis, interval, false);
    }

    /**     * Returns the list of GUIs that make up this Animated GUI
     * @return The list of GUIs
     */
    public List<? extends GUI> getGuis() {
        return this.guis;
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
     * @param plugin The Plugin to start the task with
     */
    public void start(Plugin plugin) {
        this.taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                if(AnimatedGUI.this.count++ >= AnimatedGUI.this.guis.size()) {
                    if(AnimatedGUI.this.repeat) AnimatedGUI.this.count = 0;
                    else {
                        Bukkit.getScheduler().cancelTask(AnimatedGUI.this.taskId);
                        return;
                    }
                }
                if(AnimatedGUI.this.player.isOnline() && AnimatedGUI.this.player.getOpenInventory() != null && AnimatedGUI.this.isInventory(AnimatedGUI.this.player.getOpenInventory().getTopInventory())) AnimatedGUI.this.open();
                else Bukkit.getScheduler().cancelTask(AnimatedGUI.this.taskId);
            }
        }, this.interval, this.interval);
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

    /**
     * Returns whether a specified Inventory is part of this Animated GUI
     * @param inventory The Inventory to check
     * @return Whether it's part of this Animated GUI
     */
    public boolean isInventory(Inventory inventory) {
        for(GUI gui : this.guis) {
            if(gui.getName().equals(inventory.getName())) return true;
        }
        return false;
    }

    /**
     * Opens the GUI
     */
    public void open() {
        int id = this.count-1;
        this.guis.get(id).open(this.player);
    }
}
