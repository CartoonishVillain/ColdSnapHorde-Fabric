package com.cartoonishvillain.coldsnaphorde.items.Armor;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TopHat extends ArmorItem  {
    public TopHat(ArmorMaterials materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder.tab(ColdSnapHorde.TAB));
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(new TranslatableComponent("itemtooltip.tophat.1").withStyle(ChatFormatting.AQUA));
        p_41423_.add(new TranslatableComponent("itemtooltip.tophat.2").withStyle(ChatFormatting.RED));
    }
}