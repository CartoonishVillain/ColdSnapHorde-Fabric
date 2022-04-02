package com.cartoonishvillain.coldsnaphorde.events;

import com.cartoonishvillain.coldsnaphorde.Register;
import com.cartoonishvillain.coldsnaphorde.items.FrostCharm;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Tuple;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;

import java.util.List;

public class MixinEvents {
    public static void PlayerHurt(ServerPlayer player, DamageSource source) {
        if(source.getEntity() != null && source.getEntity() instanceof LivingEntity attacker) {
            if (TrinketsApi.getTrinketComponent(player).isPresent()) {
                List<Tuple<SlotReference, ItemStack>> entry = TrinketsApi.getTrinketComponent(player).get().getAllEquipped();

                if (entry != null && !entry.isEmpty()) {
                    int level = 0;
                    for (int i = 0; i < entry.size(); i++) {
                        if (entry.get(i).getB().getItem() instanceof FrostCharm) {
                            if (((FrostCharm) entry.get(i).getB().getItem()).getTier().ordinal() > level) {
                                level = ((FrostCharm) entry.get(i).getB().getItem()).getTier().ordinal();
                            }
                        }
                    }

                    //Handled this way to calculate the highest level... For when another mod adds another charm slot, and the user decides to add both a frost and arctic wind charm, the arctic wind charm will overrule.
                    switch (level) {
                        default -> {
                        }
                        case 1 -> {
                            int chance = player.level.random.nextInt(5);
                            if (chance == 1) {
                                attacker.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 0));
                            }
                        }
                        case 2 -> {
                            int chance = player.level.random.nextInt(3);
                            if (chance == 1) {
                                attacker.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 1));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void PlayerTick(ServerPlayer player) {
        if (player.tickCount % 2 == 0 && !player.level.isClientSide && player.hasEffect(Register.FROST_EFFECT)) {
            FrostWalkerEnchantment.onEntityMoved(player, player.getLevel(), player.blockPosition(), player.getEffect(Register.FROST_EFFECT).getAmplifier()+1);
        }
    }

    public static void PlayerAttack(Player player, Entity entity) {
        if (!player.level.isClientSide && entity instanceof LivingEntity target) {
            float value = player.getAttackStrengthScale(1);
            if(player.getRandom().nextBoolean() && value == 1) {
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 0));
            }
        }
    }
}
