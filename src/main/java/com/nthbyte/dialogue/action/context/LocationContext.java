package com.nthbyte.dialogue.action.context;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LocationContext extends ResponderContext {

    private Location location;

    public LocationContext(Player player, Location location){
        super(player);
        this.location = location;
    }

    @Override
    public boolean isValid() {
        return super.isValid() && location != null;
    }

    public Location getLocation() {
        return location;
    }

}
