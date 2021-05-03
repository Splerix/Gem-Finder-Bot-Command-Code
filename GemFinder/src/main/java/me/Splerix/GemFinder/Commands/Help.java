package me.Splerix.GemFinder.Commands;

import me.Splerix.GemFinder.GemFinderVariables;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class Help extends ListenerAdapter implements GemFinderVariables {
    String botCommandsId = "814633760434946079";
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent e) {
        if (e.getChannel().getId().equals(botCommandsId))
            if (e.getMessage().getContentRaw().equalsIgnoreCase("~help")) {
                e.getChannel().sendMessage("**Gem Finder Help**\n"
                        + "~Help - Brings up the help menu\n"
                        + "~Play - Starts a brand new game for you to play\n"
                        + "**Key**\n"
                        + background + " - The area where the player can walk through (Background)\n"
                        + wall + " - The area where a player can't go (A wall)\n"
                        + player + " - The player that you move by reacting to the emojis\n"
                        + gem + " - If you get to this you beat that 'level'").queue();
            }
    }
}
