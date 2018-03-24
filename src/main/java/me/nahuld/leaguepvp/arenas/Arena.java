package me.nahuld.leaguepvp.arenas;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.World;

@Data
@AllArgsConstructor
public class Arena {
    private String name;
    private World world;
    private Location minX, minY, maxX, maxY;

    public boolean isValid() {
        return (world != null && minX != null && minY != null && maxX != null && maxY != null);
    }
}