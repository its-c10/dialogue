package com.nthbyte.dialogue.action.context;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Provides a specific action with context by giving a location.
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.3.0.0
 */
public class LocationContext extends ActionContext {

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
