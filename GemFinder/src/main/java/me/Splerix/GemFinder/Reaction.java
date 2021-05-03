package me.Splerix.GemFinder;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class Reaction  extends ListenerAdapter implements GemFinderVariables{
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent e) {
        try {
            User user = e.getUser();
            if (e.getUser().isBot()) return;
            if (!(GameManager.userPlayer.containsKey(user.getId()))) return;
            if (GameManager.userPlayer.get(user.getId()).getMessage().getId().equalsIgnoreCase(e.getMessageId())) {
                Player player = GameManager.userPlayer.get(user.getId());
                //Left
                if (e.getReactionEmote().getEmoji().equals(left)) {
                    player.getMessage().removeReaction(e.getReactionEmote().getEmoji(), e.getUser()).queue();
                    player.move('l');
                }
                //Up
                if (e.getReactionEmote().getEmoji().equals(up)) {
                    player.getMessage().removeReaction(e.getReactionEmote().getEmoji(), e.getUser()).queue();
                    player.move('u');
                }
                //Right
                if (e.getReactionEmote().getEmoji().equals(right)) {
                    player.getMessage().removeReaction(e.getReactionEmote().getEmoji(), e.getUser()).queue();
                    player.move('r');
                }
                //Down
                if (e.getReactionEmote().getEmoji().equals(down)) {
                    player.getMessage().removeReaction(e.getReactionEmote().getEmoji(), e.getUser()).queue();
                    player.move('d');
                }
                //Skip
                if (e.getReactionEmote().getEmoji().equals(skip)) {
                    player.getMessage().removeReaction(e.getReactionEmote().getEmoji(), e.getUser()).queue();
                    player.addSkips();
                    player.skipMap();
                }
                //Stop
                if (e.getReactionEmote().getEmoji().equals(stop)) {
                    player.getMessage().removeReaction(e.getReactionEmote().getEmoji(), e.getUser()).queue();
                    player.endGame();
                }
                //Statistics
                if (e.getReactionEmote().getEmoji().equals(statistics)) {
                    player.getMessage().removeReaction(e.getReactionEmote().getEmoji(), e.getUser()).queue();
                    player.toggleShowStats();
                    player.updateMap();
                }
            }
        } catch (NullPointerException exception) {}
    }
}
