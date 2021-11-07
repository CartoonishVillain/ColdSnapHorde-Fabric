package com.cartoonishvillain.coldsnaphorde.items.Armor;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;

public class TopHat extends ArmorItem  {
    public TopHat(ArmorMaterials materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder.tab(ColdSnapHorde.TAB));
    }


}