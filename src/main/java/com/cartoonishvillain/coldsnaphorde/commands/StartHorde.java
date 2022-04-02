package com.cartoonishvillain.coldsnaphorde.commands;

import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TranslatableComponent;

public class StartHorde {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("coldsnaphorde").then(Commands.literal("startHorde").requires(cs -> {return cs.hasPermission(2);})
                .then(Commands.argument("tier", IntegerArgumentType.integer(1, 3)).executes(context ->
                        startHorde(context.getSource(), IntegerArgumentType.getInteger(context, "tier"))))));
    }

    private static int startHorde(CommandSourceStack sourceStack, int level) {
        if (ColdSnapHorde.hordeDataManager.getCurrentHordeLevel() == 0) {
            try {
                switch (level) {
                    case 1 -> {
                        ColdSnapHorde.hordeTier1.SetUpHorde(sourceStack.getPlayerOrException());
                    }
                    case 2 -> {
                        ColdSnapHorde.hordeTier2.SetUpHorde(sourceStack.getPlayerOrException());
                    }
                    case 3 -> {
                        ColdSnapHorde.hordeTier3.SetUpHorde(sourceStack.getPlayerOrException());
                    }
                }
                ColdSnapHorde.hordeDataManager.setCurrentHordeLevel(level);
                sourceStack.sendSuccess(new TranslatableComponent("command.coldsnaphorde.startsuccess", level), false);
            } catch (CommandSyntaxException e) {
                Log.error(LogCategory.LOG, "startHorde can only be run by live players. Apologies for the inconvenience!");
            }
        } else {
            sourceStack.sendFailure(new TranslatableComponent("command.coldsnaphorde.startfailure"));
        }

        return 0;
    }
}
