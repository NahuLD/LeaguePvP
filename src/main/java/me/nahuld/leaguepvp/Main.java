package me.nahuld.leaguepvp;

import co.aikar.commands.*;
import co.aikar.locales.MessageKey;
import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.Getter;
import me.nahuld.leaguepvp.arenas.Arena;
import me.nahuld.leaguepvp.arenas.ArenaManager;
import me.nahuld.leaguepvp.gametypes.GameType;
import me.nahuld.leaguepvp.gametypes.GameTypeManager;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class Main extends JavaPlugin {

    @Getter private BukkitCommandManager commandManager;
    private BukkitLocales locales;

    @Getter private ArenaManager arenaManager;
    @Getter private GameTypeManager gameTypeManager;

    private Injector injector;

    @Override
    public void onEnable() {
        /* MANAGERS */
        arenaManager = new ArenaManager();
        gameTypeManager = new GameTypeManager();
        commandManager = new BukkitCommandManager(this);

        commandManager.enableUnstableAPI("help");

        /* DEPENDENCIES */
        injector = Guice.createInjector(new DependencyRegister(this));

        /* CONTEXTS */
        CommandContexts contexts = commandManager.getCommandContexts();
        contexts.registerContext(Arena.class, context -> arenaManager.getArenaByName(context.getFirstArg())
                .orElseThrow(() -> new InvalidCommandArgument(MessageKey.of("general.not-found"),
                        "{name}", context.getFirstArg())));
        contexts.registerContext(GameType.class, context -> gameTypeManager.getGameTypeByName(context.getFirstArg())
                .orElseThrow(() -> new InvalidCommandArgument(MessageKey.of("general.not-found"),
                        "{type}", "GameType", "{name}", context.getFirstArg())));

        /* LOCALES */
        this.locales = commandManager.getLocales();
        this.loadLanguages(Locale.US);

        /* COMMANDS */
        this.registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void loadLanguages(Locale... locales) {
        Arrays.stream(locales).forEach(locale -> {
            String fileName = String.format("lang/%s_%s.yml", locale.getLanguage(), locale.getCountry());
            saveResource(fileName, false);
            try {
                this.locales.loadYamlLanguageFile(new File(getDataFolder(), fileName), locale);
            } catch (IOException|InvalidConfigurationException ex) {
                ex.printStackTrace();
            }
        });
        this.locales.setDefaultLocale(Locale.US);
    }

    private void registerCommands() {
        new Reflections("me.nahuld.leaguepvp.commands").getSubTypesOf(BaseCommand.class)
                .stream()
                .map(injector::getInstance)
                .forEach(commandManager::registerCommand);
    }

}
