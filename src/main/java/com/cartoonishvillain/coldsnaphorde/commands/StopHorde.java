package com.cartoonishvillain.coldsnaphorde.commands;

import com.cartoonishvillain.cartoonishhorde.Horde;
import com.cartoonishvillain.coldsnaphorde.ColdSnapHorde;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TranslatableComponent;

public class StopHorde {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("coldsnaphorde").then(Commands.literal("stopHorde").requires(cs -> {return cs.hasPermission(2);})
                .executes(context -> stopHorde(context.getSource()))));
    }

    private static int stopHorde(CommandSourceStack sourceStack) {
        if (ColdSnapHorde.hordeDataManager.getCurrentHordeLevel() != 0) {
            switch (ColdSnapHorde.hordeDataManager.getCurrentHordeLevel()) {
                case 1 -> {
                    ColdSnapHorde.hordeTier1.Stop(Horde.HordeStopReasons.DEFEAT);
                }
                case 2 -> {
                    ColdSnapHorde.hordeTier2.Stop(Horde.HordeStopReasons.DEFEAT);
                }
                case 3 -> {
                    ColdSnapHorde.hordeTier3.Stop(Horde.HordeStopReasons.DEFEAT);
                }
            }
            sourceStack.sendSuccess(new TranslatableComponent("command.coldsnaphorde.stopsuccess"), false);
            ColdSnapHorde.hordeDataManager.setCurrentHordeLevel(0);
        } else {
            sourceStack.sendFailure(new TranslatableComponent("command.coldsnaphorde.stopfailure"));
        }

        return 0;
    }
}
