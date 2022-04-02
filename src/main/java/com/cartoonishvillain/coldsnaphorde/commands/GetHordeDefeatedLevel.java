package com.cartoonishvillain.coldsnaphorde.commands;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TranslatableComponent;

public class GetHordeDefeatedLevel {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("coldsnaphorde").then(Commands.literal("getHighestTierDefeated").requires(cs -> {return cs.hasPermission(0);})
               .executes(context -> getHorde(context.getSource()))));
    }

    private static int getHorde(CommandSourceStack sourceStack) {
        sourceStack.sendSuccess(new TranslatableComponent("command.coldsnaphorde.getsuccess", ColdSnapHorde.hordeDataManager.getHighestLevelBeaten()), false);
        return 0;
    }
}
