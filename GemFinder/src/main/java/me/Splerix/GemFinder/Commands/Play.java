package me.Splerix.GemFinder.Commands;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.Random;

public class Play extends ListenerAdapter {

    Random random = new Random();
    int key = 0;
    User user;
    boolean delOld = false;

    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().equalsIgnoreCase("~play")) {
            if (me.Splerix.GemFinder.GameManager.userPlayer.containsKey(e.getAuthor().getId())) delOld = true;
            key = random.nextInt();
            e.getChannel().sendMessage(key + "").queue();
            user = e.getAuthor();
            return;
        }
        if (e.getMessage().getContentRaw().equals(key + "")) {
            if (delOld) {
                delOld = false;
                new me.Splerix.GemFinder.GameManager(user, e.getMessage(), true);
            }
            new me.Splerix.GemFinder.GameManager(user, e.getMessage(), false);
        }
    }
}
