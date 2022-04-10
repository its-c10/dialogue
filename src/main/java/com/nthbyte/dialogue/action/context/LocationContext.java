package com.nthbyte.dialogue.action.context;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

/**
 * Context that provides the given action with a location.
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.4.1.1
 */
public class LocationContext extends ActionContext<Location> {

    public LocationContext(Location location){
        this.data = location;
    }

    public LocationContext(){}

    @Override
    public void constructData() {

        int inputStorageCount = inputStorage.size();
        /*
            2 valid situations:
                1. You specify the world, x, y, and z
                2. You specify the x, y, and z
         */
        if(inputStorageCount != 3 && inputStorageCount != 4){
            throw new IllegalStateException("The context cannot initialize its data. The input storage arguments are not valid!");
        }

        World world = null;
        String firstInput = inputStorage.get("world");
        // Could be a world name
        if(firstInput != null && !StringUtils.isNumeric(firstInput)){
            world = Bukkit.getWorld(firstInput);
        }

        if(world == null){
            world = responder.getWorld();
        }

        String xInput = inputStorage.get("x");
        String yInput = inputStorage.get("y");
        String zInput = inputStorage.get("z");
        boolean hasValidCoordinates = StringUtils.isNumeric(xInput)
                && StringUtils.isNumeric(yInput)
                && StringUtils.isNumeric(zInput);

        if(hasValidCoordinates){
            int x = Integer.parseInt(xInput);
            int y = Integer.parseInt(yInput);
            int z = Integer.parseInt(zInput);
            this.data = new Location(world, x, y, z);
        }else{
            throw new IllegalStateException("The context cannot initialize its data. The coordinates are not valid!");
        }

    }

}
