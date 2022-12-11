package com.hoprik.utils;


import com.hoprik.Bc;
import com.hoprik.screen.SettingsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.OptionsScreen;
import net.minecraft.client.gui.screen.PackScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Bc.MOD_ID)
public class AddExisting {

    @SubscribeEvent
    public static void addInGui(GuiScreenEvent.InitGuiEvent.Post event){
        if (event.getGui() instanceof OptionsScreen){

            event.addWidget(new Button(event.getGui().width / 2 - 155, event.getGui().height / 6 + 144 - 6, 150, 20, new TranslationTextComponent("options.resourcepack"), (p_213060_1_) -> {
                event.getGui().getMinecraft().displayGuiScreen(new SettingsScreen());
            }));
        }
    }
}
