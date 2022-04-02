package com.cartoonishvillain.coldsnaphorde.items.ToolsOrOther;

import com.cartoonishvillain.coldsnaphorde.Register;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum Materials implements Tier {

    ICE(1, 512, 10f, 6.0f, 8, ()->{return Ingredient.of(Register.FROSTESSENCE);}),
    ICICLE(1, 256, 10f, 1.0f, 8, ()->{return Ingredient.of(Register.ICESHARD);}),
    ICEESSENCE(1, 768, 10f, 1.0f, 8, ()->{return Ingredient.of(Register.ICEESSENCE);}),
    FROSTESSENCE(1, 256, 10f, 1.0f, 8, ()->{return Ingredient.of(Register.FROSTESSENCE);});

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private  final float attackDamage;
    private  final  int enchantability;
    private  final Supplier<Ingredient> repairMaterial;

    Materials(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial){
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getUses() {
        return maxUses;
    }

    @Override
    public float getSpeed() {
        return efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }

}
