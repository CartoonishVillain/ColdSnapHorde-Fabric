package com.cartoonishvillain.coldsnaphorde.items;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Thermometer extends Item {
    public Thermometer(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if(!level.isClientSide && interactionHand.equals(InteractionHand.MAIN_HAND)){
            float temp = player.level.getBiomeManager().getBiome(player.blockPosition()).value().getBaseTemperature();
            String code = "MISSING";
            if (temp < 0.3){code = "Cold";}
            else if(temp >= 0.3 && temp < 0.9){code = "Neutral";}
            else if(temp >= 0.9 && temp < 1.5){code = "Warm";}
            else if(temp >= 1.5){code = "Hot";}
            if(ColdSnapHorde.config.coldSnapSettings.TECHNICALTHERMOMETER){
                player.displayClientMessage(new TextComponent("Temperature: " + Float.toString(temp) + " (" + code + ")"), true);
            }
            else{
                player.displayClientMessage(new TextComponent("Temperature: " + code), true);
            }
        }
        return super.use(level, player, interactionHand);
    }
}
