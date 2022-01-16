package com.cartoonishvillain.coldsnaphorde.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LoredBlockItem extends BlockItem {
        ArrayList<Component> lore;
        public LoredBlockItem(Block p_40565_, Properties p_40566_, Component... lore) {
            super(p_40565_, p_40566_);
            this.lore = new ArrayList<>(List.of(lore));
        }

        @Override
        public void appendHoverText(ItemStack p_40572_, @Nullable Level p_40573_, List<Component> p_40574_, TooltipFlag p_40575_) {
            super.appendHoverText(p_40572_, p_40573_, p_40574_, p_40575_);
            p_40574_.addAll(lore);
        }
}
