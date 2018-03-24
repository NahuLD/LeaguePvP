package me.nahuld.leaguepvp;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.BukkitCommandManager;
import lombok.Getter;
import me.nahuld.leaguepvp.arenas.ArenaManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

public class Main extends JavaPlugin {

    @Getter private BukkitCommandManager commandManager;

    @Getter private ArenaManager arenaManager;

    @Override
    public void onEnable() {
        arenaManager = new ArenaManager();

        commandManager = new BukkitCommandManager(this);
        new Reflections("me.nahuld.leaguepvp.commands").getSubTypesOf(BaseCommand.class)
                .forEach(command -> commandManager.registerCommand(command.cast(BaseCommand.class)));
    }

    @Override
    public void onDisable() {

    }
}
