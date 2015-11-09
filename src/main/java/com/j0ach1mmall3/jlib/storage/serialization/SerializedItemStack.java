package com.j0ach1mmall3.jlib.storage.serialization;

import com.j0ach1mmall3.jlib.methods.Parsing;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/11/2015
 */
public final class SerializedItemStack implements JLibSerializable {
    private ItemStack itemStack;
    private String string;

    /**
     * Constructs a new SerializedItemStack
     * @param itemStack The ItemStack
     * @see ItemStack
     */
    public SerializedItemStack(ItemStack itemStack) {
        this.itemStack = itemStack!=null?itemStack:new ItemStack(Material.AIR);
        string = new SerializedMaterialData(this.itemStack.getData()).getString() + "|||" + this.itemStack.getAmount() + "|||" + this.itemStack.getDurability() + "|||" + new SerializedItemMeta(this.itemStack.getItemMeta()).getString();
    }

    /**
     * Constructs a new SerializedItemStack
     * @param string The String
     */
    public SerializedItemStack(String string) {
        String[] splitted = string.split("\\|\\|\\|");
        this.itemStack = new ItemStack(Material.AIR);
        itemStack.setData(new SerializedMaterialData(splitted[0]).getMaterialData());
        itemStack.setAmount(Parsing.parseInt(splitted[1]));
        itemStack.setDurability(Parsing.parseShort(splitted[2]));
        itemStack.setItemMeta(new SerializedItemMeta(splitted[3]).getItemMeta());
        this.string = string;
    }

    /**
     * Returns the ItemStack
     * @return The ItemStack
     * @see ItemStack
     */
    public ItemStack getItemStack() {
        return itemStack;
    }


    /**
     * @see JLibSerializable#getString()
     */
    public String getString() {
        return string;
    }
}