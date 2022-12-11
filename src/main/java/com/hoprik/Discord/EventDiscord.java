package com.hoprik.Discord;

import com.hoprik.Bc;
import com.hoprik.screen.guiFucn;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Bc.MOD_ID)
public class EventDiscord {
    static Discord discordRPC = new Discord("1002984185872531537");
    static boolean isUse = false;
    static Minecraft minecraft = Minecraft.getInstance();
    static int tick = 0;

    public static void move(){
        Bc.isShow = guiFucn.switchBolen(Bc.isShow);
        Bc.LOGGER.info(Bc.isShow);
        if (!Bc.isShow) {
            discordRPC.Stop();
        }
        else {
            discordRPC = new Discord("1002984185872531537");
            discordRPC.UpdatePresents("On the main menu", "", "");
        }
    }

    @SubscribeEvent
    public static void onMainMenu(GuiScreenEvent event){
        if (Bc.isShow) {
            if (event.getGui() instanceof MainMenuScreen) {
                discordRPC.UpdatePresents("On the main menu", "", "");
            } else if (event.getGui() instanceof MultiplayerScreen) {
                discordRPC.UpdatePresents("Chooses the server", "", "");
            } else if (event.getGui() instanceof WorldSelectionScreen) {
                discordRPC.UpdatePresents("Chooses the world", "", "");
            } else if (event.getGui() instanceof ConnectingScreen) {
                discordRPC.UpdatePresents("Connecting to the server", "", "");
            } else if (event.getGui() instanceof WorldLoadProgressScreen) {
                discordRPC.UpdatePresents("Loaded into the world", "", "");
            }
        }
    }

    @SubscribeEvent
    public static void playerDimension(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (tick == 100)
            try {
                assert minecraft.player != null;
                RegistryKey<World> key = minecraft.player.world.getDimensionKey();
                boolean isSingl = minecraft.isSingleplayer();
                if (!isSingl) {
                    if (key == World.OVERWORLD) {
                        discordRPC.UpdatePresents("Playing on the server", "otherworld", "Over world");
                    } else if (key == World.THE_NETHER) {
                        discordRPC.UpdatePresents("Playing on the server", "nether", "Nether");
                    } else if (key == World.THE_END) {
                        discordRPC.UpdatePresents("Playing on the server", "end", "End");
                    }
                } else {
                    if (key == World.OVERWORLD) {
                        discordRPC.UpdatePresents("Playing on the singleplayer", "otherworld", "Over world");
                    } else if (key == World.THE_NETHER) {
                        discordRPC.UpdatePresents("Playing on the singleplayer", "nether", "Nether");
                    } else if (key == World.THE_END) {
                        discordRPC.UpdatePresents("Playing on the singleplayer", "end", "End");
                    }
                    tick = 0;
                }
            } catch (Exception e) {
                tick = 0;

            }
            else {
                tick++;
            }
        }
    }


}
