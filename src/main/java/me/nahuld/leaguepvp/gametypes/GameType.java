package me.nahuld.leaguepvp.gametypes;

import lombok.Data;
import me.nahuld.leaguepvp.arenas.Arena;
import org.bukkit.inventory.ItemStack;

@Data
public class GameType {
    private String name;
    private ItemStack icon;
    private Arena[] ignored;
}
