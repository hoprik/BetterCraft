package com.hoprik.Discord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class Discord {

    public Discord(String applicationId){
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        DiscordRPC.discordInitialize(applicationId,handlers, true);

    }

    public void UpdatePresents(String details, String keySmall, String keyText){
        DiscordRichPresence rich = new DiscordRichPresence.Builder("").setDetails(details).setBigImage("minecraft", "Майнкрафт").setSmallImage(keySmall, keyText).build();
        DiscordRPC.discordUpdatePresence(rich);
    }
}
