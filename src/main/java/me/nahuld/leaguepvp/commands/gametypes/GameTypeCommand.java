package me.nahuld.leaguepvp.commands.gametypes;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.locales.MessageKey;
import com.google.inject.Inject;
import me.nahuld.leaguepvp.gametypes.GameType;
import me.nahuld.leaguepvp.gametypes.GameTypeManager;

@CommandAlias("gametype|gt")
public class GameTypeCommand extends BaseCommand {

    private GameTypeManager gameTypeManager;

    @Inject
    public GameTypeCommand(GameTypeManager gameTypeManager) {
        this.gameTypeManager = gameTypeManager;
    }

    @HelpCommand
    @Subcommand("help")
    public void doHelp(CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("create")
    @Description("gametype-command.create-subcommand.description")
    public void create(String name) {
        if (!gameTypeManager.add(name))
            getCurrentCommandIssuer().sendInfo(MessageKey.of("gametype-command.create-subcommand.existing-gametype"),
                    "{name}", name);
        else getCurrentCommandIssuer().sendInfo(MessageKey.of("gametype-command.create-subcommand.created"),
                "{name}", name);
    }

    @Subcommand("remove")
    @Description("gametype-command.remove-command.description")
    public void remove(GameType gameType) {
        gameTypeManager.getGameTypeSet().remove(gameType);
        getCurrentCommandIssuer().sendInfo(MessageKey.of("gametype-command.remove-subcommand.removed"),
                "{name}", gameType.getName());
    }
}
