package com.hoprik.Discord;

import com.hoprik.Bc;
import com.sun.media.jfxmedia.track.Track;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import org.apache.commons.lang3.StringEscapeUtils;

import java.nio.charset.StandardCharsets;

public class Discord {

    public Discord(String applicationId){
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        DiscordRPC.discordInitialize(applicationId,handlers, true);

    }

    public void UpdatePresents(String details, String keySmall, String keyText){
        String detal = new String(details.getBytes(), StandardCharsets.UTF_8);
        DiscordRichPresence rich = new DiscordRichPresence.Builder("").setDetails(detal).setBigImage("minecraft", StringEscapeUtils.escapeJson("Minecraft")).setSmallImage(keySmall, StringEscapeUtils.escapeJson(keyText)).build();
        DiscordRPC.discordUpdatePresence(rich);
    }

    public void Stop(){
        DiscordRPC.discordShutdown();
    }
}
