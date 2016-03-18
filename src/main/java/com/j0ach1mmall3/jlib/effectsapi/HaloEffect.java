package com.j0ach1mmall3.jlib.effectsapi;

import com.j0ach1mmall3.jlib.effectsapi.util.Util;
import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 13/03/2016
 */
public final class HaloEffect extends Effect {
    private final int particleCount;
    private final Vector[] locations;

    private int i;

    public HaloEffect(Location l, org.bukkit.Effect effect, int id, int data, float speed, int viewDistance, double radius, int particleCount) {
        super(l, effect, id, data, speed, viewDistance, 1);
        this.particleCount = particleCount;
        this.locations = Util.getCircle(radius, this.particleCount);
    }

    @Override
    public void run() {
        this.l.getWorld().spigot().playEffect(this.l.clone().add(this.locations[this.i]), this.effect, this.id, this.data, 0, 0, 0, this.speed, 1, this.viewDistance);
        this.i++;
        if(this.i >= this.locations.length) this.cancel();
    }
}