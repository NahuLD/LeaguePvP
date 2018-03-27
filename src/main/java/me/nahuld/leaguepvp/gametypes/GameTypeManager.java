package me.nahuld.leaguepvp.gametypes;

import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.Optional;
import java.util.Set;

public class GameTypeManager {
    @Getter private Set<GameType> gameTypeSet = Sets.newHashSet();

    public boolean add(String gameTypeName) {
        return add(new GameType(gameTypeName));
    }

    public boolean add(GameType newGameType) {
        if (!getGameTypeByName(newGameType.getName()).isPresent()) {
            gameTypeSet.add(newGameType);
            return true;
        } else return false;
    }

    public Optional<GameType> getGameTypeByName(String gameTypeName) {
        return gameTypeSet.stream().filter(gameType -> gameType.getName().equalsIgnoreCase(gameTypeName)).findFirst();
    }
}
