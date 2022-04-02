package com.cartoonishvillain.coldsnaphorde.items;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.cartoonishvillain.coldsnaphorde.Utils;
import net.minecraft.ChatFormatting;
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

public class Snowglobe extends Item {
    Tier tier;

    public Snowglobe(Properties properties, Tier tier) {
        super(properties);
        this.tier = tier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        if(handIn == InteractionHand.MAIN_HAND && !worldIn.isClientSide() && playerIn != null && tier.equals(Tier.ONE)) {
            startTier1(worldIn, playerIn);
        } else if(handIn == InteractionHand.MAIN_HAND && !worldIn.isClientSide() && playerIn != null && tier.equals(Tier.TWO)) {
            startTier2(worldIn, playerIn);
        } else if(handIn == InteractionHand.MAIN_HAND && !worldIn.isClientSide() && playerIn != null && tier.equals(Tier.THREE)) {
            startTier3(worldIn, playerIn);
        }
        return super.use(worldIn, playerIn, handIn);
    }

    private void startTier1(Level worldIn, Player playerIn) {
        if ((Utils.tier1Valid(worldIn, playerIn.blockPosition()))) {
            int cooldown = ColdSnapHorde.hordeDataManager.getCooldownTicks();

            if(cooldown == 0 && !ColdSnapHorde.hordeTier1.getHordeActive()) {
                ColdSnapHorde.hordeTier1.SetUpHorde((ServerPlayer) playerIn);
                worldIn.playSound(null, playerIn.blockPosition(), SoundEvents.TRIDENT_RIPTIDE_1, SoundSource.PLAYERS, 0.5f, 1.5f);
                playerIn.getMainHandItem().shrink(1);
            } else if (cooldown > 0) {
                playerIn.displayClientMessage(new TextComponent("Horde on cooldown! Returning in: " + TimeBuilder(cooldown)), false);
            } else if(cooldown < 0) {
                playerIn.displayClientMessage(new TextComponent("The Horde is busy elsewhere. Try again later!"), false);
            }
        } else {
            playerIn.displayClientMessage(new TextComponent("This tier of horde can not be summoned in this " +
                    "climate! Can not spawn in swamps, nether, or the end. It may also be too hot in your current biome!"), false);
        }
    }

    private void startTier2(Level worldIn, Player playerIn) {
        if ((Utils.tier2Valid(worldIn, playerIn.blockPosition()))) {
            int cooldown = ColdSnapHorde.hordeDataManager.getCooldownTicks();

            if(cooldown == 0 && !ColdSnapHorde.hordeTier2.getHordeActive()) {
                ColdSnapHorde.hordeTier2.SetUpHorde((ServerPlayer) playerIn);
                worldIn.playSound(null, playerIn.blockPosition(), SoundEvents.TRIDENT_RIPTIDE_1, SoundSource.PLAYERS, 0.5f, 1.5f);
                playerIn.getMainHandItem().shrink(1);
            } else if (cooldown > 0) {
                playerIn.displayClientMessage(new TextComponent("Horde on cooldown! Returning in: " + TimeBuilder(cooldown)), false);
            } else if(cooldown < 0) {
                playerIn.displayClientMessage(new TextComponent("The Horde is busy elsewhere. Try again later!"), false);
            }
        } else {
            playerIn.displayClientMessage(new TextComponent("This tier of horde can not be summoned in this " +
                    "climate! Can not spawn in the nether, or the end. It may also be too hot in your current biome!"), false);
        }
    }

    private void startTier3(Level worldIn, Player playerIn) {
        if ((Utils.tier3Valid(worldIn, playerIn.blockPosition()))) {
            int cooldown = ColdSnapHorde.hordeDataManager.getCooldownTicks();

            if(cooldown == 0 && !ColdSnapHorde.hordeTier3.getHordeActive()) {
                ColdSnapHorde.hordeTier3.SetUpHorde((ServerPlayer) playerIn);
                worldIn.playSound(null, playerIn.blockPosition(), SoundEvents.TRIDENT_RIPTIDE_1, SoundSource.PLAYERS, 0.5f, 1.5f);
                playerIn.getMainHandItem().shrink(1);
            } else if (cooldown > 0) {
                playerIn.displayClientMessage(new TextComponent("Horde on cooldown! Returning in: " + TimeBuilder(cooldown)), false);
            } else if(cooldown < 0) {
                playerIn.displayClientMessage(new TextComponent("The Horde is busy elsewhere. Try again later!"), false);
            }
        } else {
            playerIn.displayClientMessage(new TextComponent("This tier of horde can not be summoned in this " +
                    "climate! Your biome may be too too hot in your current biome!"), false);
        }
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
        switch (tier) {
            default -> {
                p_41423_.add(new TranslatableComponent("itemtooltip.snowglobe.2.1").withStyle(ChatFormatting.AQUA));
                p_41423_.add(new TranslatableComponent("itemtooltip.snowglobe.3").withStyle(ChatFormatting.RED));
                p_41423_.add(new TranslatableComponent("itemtooltip.coldsnaphorde.tier.1").withStyle(ChatFormatting.AQUA));
            }
            case TWO -> {
                p_41423_.add(new TranslatableComponent("itemtooltip.snowglobe.2.2").withStyle(ChatFormatting.AQUA));
                p_41423_.add(new TranslatableComponent("itemtooltip.snowglobe.3").withStyle(ChatFormatting.RED));
                p_41423_.add(new TranslatableComponent("itemtooltip.snowglobe.4.2").withStyle(ChatFormatting.BLUE));
                p_41423_.add(new TranslatableComponent("itemtooltip.coldsnaphorde.tier.2").withStyle(ChatFormatting.AQUA));
            }
            case THREE -> {
                p_41423_.add(new TranslatableComponent("itemtooltip.snowglobe.2.3").withStyle(ChatFormatting.AQUA));
                p_41423_.add(new TranslatableComponent("itemtooltip.snowglobe.3").withStyle(ChatFormatting.RED));
                p_41423_.add(new TranslatableComponent("itemtooltip.snowglobe.4.3").withStyle(ChatFormatting.BLUE));
                p_41423_.add(new TranslatableComponent("itemtooltip.coldsnaphorde.tier.3").withStyle(ChatFormatting.AQUA));
            }
        }
    }
}
