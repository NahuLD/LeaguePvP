package me.nahuld.leaguepvp.arenas;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Location;

@Data
@AllArgsConstructor
class Arena {
    private String name;
    private Location playerOne, playerTwo;
}