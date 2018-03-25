package me.nahuld.leaguepvp;

import co.aikar.commands.*;
import lombok.Getter;
import me.nahuld.leaguepvp.arenas.ArenaManager;
import me.nahuld.leaguepvp.commands.arenas.ArenaCommand;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class Main extends JavaPlugin {

    @Getter private BukkitCommandManager commandManager;

    @Getter private ArenaManager arenaManager;

    @Override
    public void onEnable() {
        arenaManager = new ArenaManager();

        commandManager = new BukkitCommandManager(this);

        this.loadLanguages(Locale.US);
        commandManager.getLocales().setDefaultLocale(Locales.ENGLISH);

        this.registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void loadLanguages(Locale... locales) {
        Arrays.stream(locales).forEach(locale -> {
            String fileName = String.format("%s_%s.yml", locale.getISO3Language(), locale.getISO3Country());
            try {
                commandManager.getLocales().loadYamlLanguageFile(new File(getDataFolder(), fileName), locale);
            } catch (IOException|InvalidConfigurationException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void registerCommands() {
        new Reflections("me.nahuld.leaguepvp.commands").getSubTypesOf(BaseCommand.class)
                .forEach(command -> commandManager.registerCommand(command.cast(BaseCommand.class)));
    }

}
