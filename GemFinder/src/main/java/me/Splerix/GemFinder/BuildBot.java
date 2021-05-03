package me.Splerix.GemFinder;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class BuildBot {

    public JDABuilder builder;

    public BuildBot() throws LoginException {
        String token = "I put the discord bot's key token here";
        builder = JDABuilder.createDefault(token);

        //Commands
        builder.addEventListeners(new me.Splerix.GemFinder.Commands.Help());
        builder.addEventListeners(new me.Splerix.GemFinder.Commands.Play());
        //Listeners
        builder.addEventListeners(new me.Splerix.GemFinder.Reaction());

        //Setup Build
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setActivity(Activity.playing("Gem Finder ~Play"));
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.build();
    }
}
