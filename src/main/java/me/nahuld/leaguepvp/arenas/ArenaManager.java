package me.nahuld.leaguepvp.arenas;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class ArenaManager {
    @Getter private LinkedList<Arena> arenaList = Lists.newLinkedList();

    public Optional<Arena> getArenaByName(final String name) {
        return arenaList.stream().filter(arena -> arena.getName().equalsIgnoreCase(name)).findFirst();
    }

    public Optional<Arena> getArenaById(final int id) {
        return Optional.ofNullable(arenaList.get(id));
    }

    public Arena getRandomArena() {
        return arenaList.get(ThreadLocalRandom.current().nextInt(arenaList.size()));
    }
}
