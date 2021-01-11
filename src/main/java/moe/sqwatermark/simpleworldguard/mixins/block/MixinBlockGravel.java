package moe.sqwatermark.simpleworldguard.mixins.block;

import moe.sqwatermark.simpleworldguard.config.WorldGuardConfig;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(BlockGravel.class)
public class MixinBlockGravel extends BlockFalling {

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!WorldGuardConfig.physics.canGravelFall) return;
        super.updateTick(worldIn, pos, state, rand);
    }

}
