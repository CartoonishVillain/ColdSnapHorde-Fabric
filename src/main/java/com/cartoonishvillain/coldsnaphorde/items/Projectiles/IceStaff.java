package com.cartoonishvillain.coldsnaphorde.items.Projectiles;

import com.cartoonishvillain.coldsnaphorde.Register;
import com.cartoonishvillain.coldsnaphorde.entities.projectiles.IceProjectile;
import com.cartoonishvillain.coldsnaphorde.items.Tier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IceStaff extends TieredItem {
    Tier tier;

    public IceStaff(net.minecraft.world.item.Tier itemTier, Properties p_41383_, Tier tier) {
        super(itemTier, p_41383_);
        this.tier = tier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide && hand.equals(InteractionHand.MAIN_HAND)) {
            ItemStack itemStack = player.getItemInHand(hand);
            itemStack.hurtAndBreak(1, player, (p_32290_) -> p_32290_.broadcastBreakEvent(hand));
            player.playSound(SoundEvents.FIRECHARGE_USE, 1, 1);

            if(tier.equals(Tier.THREE)) {
                Tier sendTier;
                if(level.random.nextInt(5) == 1) {
                    sendTier = Tier.THREE;
                } else {
                    sendTier = Tier.TWO;
                }

                IceProjectile iceEntity = new IceProjectile(Register.ICEPROJECTILE, level, player, sendTier);
                iceEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                level.addFreshEntity(iceEntity);
            } else {
                IceProjectile iceEntity = new IceProjectile(Register.ICEPROJECTILE, level, player, Tier.ONE);
                iceEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                level.addFreshEntity(iceEntity);
            }
            level.playSound(null, player.getX(), player.getY(1), player.getZ(), SoundEvents.ZOMBIE_VILLAGER_CURE, SoundSource.PLAYERS, 0.5f, 2);
            player.getCooldowns().addCooldown(this, 50);
        }


        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(new TranslatableComponent("itemtooltip.icestaff.1").withStyle(ChatFormatting.AQUA));
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
