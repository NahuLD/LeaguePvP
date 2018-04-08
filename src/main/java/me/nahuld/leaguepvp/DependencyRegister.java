package me.nahuld.leaguepvp;

import com.google.inject.AbstractModule;
import me.nahuld.leaguepvp.arenas.ArenaManager;
import me.nahuld.leaguepvp.gametypes.GameTypeManager;

public class DependencyRegister extends AbstractModule {

    private Main main;

    DependencyRegister(Main main) {
        this.main = main;
    }

    @Override
    public void configure() {
        bind(ArenaManager.class).toInstance(main.getArenaManager());
        bind(GameTypeManager.class).toInstance(main.getGameTypeManager());
    }
}
