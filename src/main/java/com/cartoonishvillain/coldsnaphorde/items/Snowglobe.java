package com.cartoonishvillain.coldsnaphorde.items;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.component.WorldCooldownComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.cartoonishvillain.coldsnaphorde.component.ComponentStarter.WORLDCOMPONENT;

public class Snowglobe extends Item {
    public Snowglobe(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        if(handIn == InteractionHand.MAIN_HAND && !worldIn.isClientSide() && playerIn != null) {
            if (biomeCheck(worldIn, playerIn.blockPosition()) || worldIn.getBiome(playerIn.blockPosition()).toString().contains("swamp") || worldIn.dimension().toString().contains("end") || worldIn.dimension().toString().contains("nether")) {
                AtomicInteger atomicInteger = new AtomicInteger(0);
                WorldCooldownComponent h = WORLDCOMPONENT.get(worldIn);
                    if(h.getCooldownTicks() > 0){
                        atomicInteger.set(h.getCooldownTicks());
                    }

                if(atomicInteger.get() == 0 && !ColdSnapHorde.Horde.getHordeActive()) {
                    ColdSnapHorde.Horde.SetUpHorde((ServerPlayer) playerIn);
                    worldIn.playSound(null, playerIn.blockPosition(), SoundEvents.TRIDENT_RIPTIDE_1, SoundSource.PLAYERS, 0.5f, 1.5f);
                    playerIn.getMainHandItem().shrink(1);
                }else if (!ColdSnapHorde.Horde.getHordeActive()){
                    playerIn.displayClientMessage(new TextComponent("Horde on cooldown! Returning in: " + TimeBuilder(atomicInteger.get())), false);
                }else if(ColdSnapHorde.Horde.getHordeActive()){
                    playerIn.displayClientMessage(new TextComponent("The Horde is busy elsewhere. Try again later!"), false);
                }
            }else{
                playerIn.displayClientMessage(new TextComponent("Temperature too hot for the horde to summon!"), false);
            }
        }
        return super.use(worldIn, playerIn, handIn);
    }

    private boolean biomeCheck(Level world, BlockPos pos){
        int protlvl = ColdSnapHorde.config.spawnconfig.HEATPROT;
        float temp = world.getBiome(pos).value().getBaseTemperature();
        int code = -1;
        if (temp < 0.3){code = 0;}
        else if(temp >= 0.3 && temp < 0.9){code = 1;}
        else if(temp >= 0.9 && temp < 1.5){code = 2;}
        else if(temp >= 1.5){code = 3;}

        return code <= protlvl;
    }

    private String TimeBuilder(int duration){
        String timer = "(";
        int timermath = duration/20;
        if (timermath >= 60){
            timer += Integer.toString(timermath/60);
            while(timermath >= 60){timermath -= 60;}
            timer += ":";
        }else{
            timer += "00:";
        }
        if (timermath > 9){
            timer += Integer.toString(timermath);
            timer += ")";
        } else{
            timer += "0";
            timer += Integer.toString(timermath);
            timer += ")";
        }
        return timer;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(new TranslatableComponent("itemtooltip.snowglobe.1").withStyle(ChatFormatting.AQUA));
        p_41423_.add(new TranslatableComponent("itemtooltip.snowglobe.2").withStyle(ChatFormatting.AQUA));
        p_41423_.add(new TranslatableComponent("itemtooltip.snowglobe.3").withStyle(ChatFormatting.RED));
        p_41423_.add(new TranslatableComponent("itemtooltip.snowglobe.4").withStyle(ChatFormatting.RED));
    }
}
