package me.nahuld.leaguepvp.arenas;

import lombok.Data;
import org.bukkit.Location;

@Data
public class Arena {
    private String name;
    private Location playerOne, playerTwo;
}