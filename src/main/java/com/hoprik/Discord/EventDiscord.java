package com.hoprik.Discord;

import com.hoprik.Bc;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
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

    @SubscribeEvent
    public static void onMainMenu(GuiScreenEvent event){
        if (event.getGui() instanceof MainMenuScreen){
            discordRPC.UpdatePresents("В главном меню", "", "");
            isUse = true;
        }
        else if (event.getGui() instanceof MultiplayerScreen){
            discordRPC.UpdatePresents("Выбирает сервер", "", "");
            isUse = true;
        }
        else if (event.getGui() instanceof WorldSelectionScreen){
            discordRPC.UpdatePresents("Выбирает мир", "", "");
            isUse = true;
        }
        else if (event.getGui() instanceof ConnectingScreen){
            discordRPC.UpdatePresents("Подключается к серверу", "", "");
            isUse = true;
        }
        else if (event.getGui() instanceof WorldLoadProgressScreen){
            discordRPC.UpdatePresents("Загружается в мир", "", "");
            isUse = true;
        }
    }

    @SubscribeEvent
    public static void playerDimension(TickEvent.PlayerTickEvent event) {
        Bc.LOGGER.info(tick);
        if (event.phase == TickEvent.Phase.END) {
            if (tick == 100)
            try {
                assert minecraft.player != null;
                RegistryKey<World> key = minecraft.player.world.getDimensionKey();
                boolean isSingl = minecraft.isSingleplayer();
                if (!isSingl) {
                    if (key == World.OVERWORLD) {
                        discordRPC.UpdatePresents("Играет на сервере", "otherworld", "Обычный мир");
                    } else if (key == World.THE_NETHER) {
                        discordRPC.UpdatePresents("Играет на сервере", "nether", "Незер");
                    } else if (key == World.THE_END) {
                        discordRPC.UpdatePresents("Играет на сервере", "end", "Край");
                    }
                } else {
                    if (key == World.OVERWORLD) {
                        discordRPC.UpdatePresents("Играет в одиночной игре", "otherworld", "Обычный мир");
                    } else if (key == World.THE_NETHER) {
                        discordRPC.UpdatePresents("Играет в одиночной игре", "nether", "Незер");
                    } else if (key == World.THE_END) {
                        discordRPC.UpdatePresents("Играет в одиночной игре", "end", "Край");
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
