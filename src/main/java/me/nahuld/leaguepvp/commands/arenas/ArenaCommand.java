package me.nahuld.leaguepvp.commands.arenas;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import co.aikar.locales.MessageKey;
import me.nahuld.leaguepvp.arenas.Arena;
import me.nahuld.leaguepvp.arenas.ArenaManager;
import org.bukkit.entity.Player;

@CommandAlias("arenas|arena")
public class ArenaCommand extends BaseCommand {
    @Dependency private ArenaManager arenaManager;

    @HelpCommand
    @Subcommand("help")
    public void doHelp(CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("create")
    @Description("arena-command.arena-subcommand.create.description")
    @HelpSearchTags("create")
    public void create(String name) {
        if (arenaManager.getArenaByName(name).isPresent()) {
            getCurrentCommandIssuer().sendError(MessageKey.of("arena-command.create-subcommand.existing-arena"), "{name}", name);
            return;
        }
        Arena newArena = new Arena();
        newArena.setName(name);
        arenaManager.getArenaList().add(newArena);
        getCurrentCommandIssuer().sendInfo(MessageKey.of("arena-command.create-subcommand.successfully-created"), "{name}", name);
    }

    @Subcommand("remove")
    @Description("arena-command.remove-subcommand.description")
    @HelpSearchTags("remove")
    public void remove(Arena arena) {
        arenaManager.getArenaList().remove(arena);
        getCurrentCommandIssuer().sendInfo(MessageKey.of("arena-command.remove-subcommand.successfully-removed"), "{name}", arena.getName());
    }

    @Subcommand("rename")
    @Description("arena-command.rename-command.description")
    @HelpSearchTags("rename")
    public void rename(Arena arena, String newName) {
        getCurrentCommandIssuer().sendInfo(MessageKey.of("arena-command.rename-subcommand.name-changed"),
                "{oldName}", arena.getName(), "{newName}", newName);
        arena.setName(newName);
    }

    @Subcommand("setpos")
    @Description("arena-command.setpos-subcommand.description")
    public void setPos(Player player, int position, Arena arena) {
        switch (position) {
            case 2:
                arena.setPlayerTwo(player.getLocation());
                getCurrentCommandIssuer().sendInfo(MessageKey.of("arena-command.setpos-subcommand.position-set"),
                        "{pos}", String.valueOf(position));
            case 1:
                arena.setPlayerOne(player.getLocation());
                getCurrentCommandIssuer().sendInfo(MessageKey.of("arena-command.setpos-subcommand.position-set"),
                        "{pos}", String.valueOf(position));
            default: {
                getCurrentCommandIssuer().sendInfo(MessageKey.of("general.invalid-argument"),
                        "{arg}", String.valueOf(position));
                return;
            }
        }
    }
}
