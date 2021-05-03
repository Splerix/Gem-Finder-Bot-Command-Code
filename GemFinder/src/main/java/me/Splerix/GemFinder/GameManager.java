package me.Splerix.GemFinder;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import java.util.HashMap;
import java.util.Map;

public class GameManager implements GemFinderVariables{

    public static Map<String, Player> userPlayer = new HashMap<String, Player>();
    public GameManager(User user, Message message, boolean delOld) {
        if (delOld) { userPlayer.get(user.getId()).endGame(); }
        message.editMessage("**Gem Finder is getting ready, please wait, **" + user.getAsMention()).queue();
        Player player = new Player(message, user);
        player.addReactions();
        player.skipMap();
        userPlayer.put(user.getId(), player);
    }
}