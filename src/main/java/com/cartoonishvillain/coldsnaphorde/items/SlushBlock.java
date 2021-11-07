package com.cartoonishvillain.coldsnaphorde.items;

import com.cartoonishvillain.coldsnaphorde.entities.mobs.basemob.GenericHordeMember;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;


public class SlushBlock extends Block {
    private static final VoxelShape voxelShape = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    public SlushBlock(Properties properties) {
        super(properties);
    }
    private int ticks = 0;

    @Override
    public boolean canSurvive(BlockState p_60525_, LevelReader p_60526_, BlockPos p_60527_) {
        BlockPos blockpos = p_60527_.below();
        return p_60526_.getBlockState(blockpos).isFaceSturdy(p_60526_, blockpos, Direction.UP);
    }



    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }


    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
        super.randomTick(state, worldIn, pos, random);
        worldIn.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
    }

    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        if(!(entityIn instanceof GenericHordeMember))
            entityIn.makeStuckInBlock(state, new Vec3(0.85D, 0.5D, 0.85D));

    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return voxelShape;
    }


}
