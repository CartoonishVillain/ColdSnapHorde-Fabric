package com.cartoonishvillain.coldsnaphorde.items;

import com.cartoonishvillain.coldsnaphorde.Register;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FrostWalkStaff extends TieredItem {
    Tier tier;

    public FrostWalkStaff(net.minecraft.world.item.Tier itemTier, Properties p_41383_, Tier tier) {
        super(itemTier, p_41383_);
        this.tier = tier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide && hand.equals(InteractionHand.MAIN_HAND)) {
            if (tier.equals(Tier.THREE)) {
                player.addEffect(new MobEffectInstance(Register.FROST_EFFECT, 15*20, 2));
            } else {
                player.addEffect(new MobEffectInstance(Register.FROST_EFFECT, 15*20, 1));
            }

            ItemStack itemStack = player.getItemInHand(hand);

            itemStack.hurtAndBreak(1, player, (p_32290_) -> p_32290_.broadcastBreakEvent(hand));
            player.playSound(SoundEvents.FIRECHARGE_USE, 1, 1);

            player.getCooldowns().addCooldown(this, 50);
        }
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(new TranslatableComponent("itemtooltip.icewalker.1").withStyle(ChatFormatting.AQUA));
        switch (tier) {
            default -> {
                p_41423_.add(new TranslatableComponent("itemtooltip.coldsnaphorde.tier.1").withStyle(ChatFormatting.AQUA));
            }
            case TWO -> {
                p_41423_.add(new TranslatableComponent("itemtooltip.coldsnaphorde.tier.2").withStyle(ChatFormatting.AQUA));
            }
            case THREE -> {
                p_41423_.add(new TranslatableComponent("itemtooltip.coldsnaphorde.tier.3").withStyle(ChatFormatting.AQUA));
            }
        }
    }
}
