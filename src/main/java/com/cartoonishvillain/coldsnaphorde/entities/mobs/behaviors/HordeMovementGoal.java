package com.cartoonishvillain.coldsnaphorde.entities.mobs.behaviors;

import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.GenericHordeMember;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

//based on move towards raid goal


public class HordeMovementGoal<T extends GenericHordeMember> extends Goal {
    private final T Member;

    public HordeMovementGoal(T member){
        this.Member = member;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return this.Member.getLoc() != null && this.Member.isHordeMember() && this.Member.getTarget() == null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.Member.getLoc() != null && this.Member.isHordeMember() && this.Member.getTarget() == null;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.Member.isPathFinding()) {
            Vec3 vector3d = DefaultRandomPos.getPosTowards(this.Member, 15, 4, Vec3.atBottomCenterOf(Member.getLoc()), (double)((float)Math.PI / 10F));
            if (vector3d != null) {
                this.Member.getNavigation().moveTo(vector3d.x, vector3d.y, vector3d.z, 0.5D);
            }
        }
    }
}
