package me.nahuld.leaguepvp.gametypes;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.nahuld.leaguepvp.arenas.Arena;
import org.bukkit.inventory.ItemStack;

//TODO: Add support for kit editing
@Data
@RequiredArgsConstructor
public class GameType {
    private final String name;
    private ItemStack icon;
    private Arena[] ignored;
}
