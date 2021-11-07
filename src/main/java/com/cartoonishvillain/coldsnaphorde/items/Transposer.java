package com.cartoonishvillain.coldsnaphorde.items;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class Transposer extends Item {
    public Transposer(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if(!level.isClientSide && interactionHand.equals(InteractionHand.MAIN_HAND)&& player.isCrouching() && player.getMainHandItem().getItem() instanceof Transposer){
            ItemStack itemStack = player.getMainHandItem();
            itemStack.shrink(1);
            EntityType.LIGHTNING_BOLT.spawn((ServerLevel) player.getCommandSenderWorld(), new ItemStack(Items.AIR), null, player.getOnPos(), MobSpawnType.TRIGGERED, true, false);}
        return super.use(level, player, interactionHand);
    }
}
