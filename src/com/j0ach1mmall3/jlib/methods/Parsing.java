package com.j0ach1mmall3.jlib.methods;

import org.bukkit.Material;

public class Parsing {
	public static int parseString(String s){
		int i;
		try {
            i = Integer.parseInt(s);
		} catch (NumberFormatException e) {
            return 0;
        }
        return i;
	}
	
	public static String parseInt(int i){
		return String.valueOf(i);
	}
	
	@SuppressWarnings("deprecation")
	public static Material parseMaterial(String item){
        if(item == null) return Material.AIR;
        if(item.equals("")) return Material.AIR;
		return Material.getMaterial(parseString(item.split(":")[0]));
	}
	
	public static int parseData(String item){
        if(item == null) return 0;
		if(item.equals("")) return 0;
		if(!item.contains(":")) return 0;
		if(item.endsWith(":")) return 0;
		return Integer.valueOf(item.split(":")[1]);
	}
}
