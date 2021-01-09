package moe.sqwatermark.worldguard.mixins;

import moe.sqwatermark.config.WorldGuardConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BlockVine.class)
public class MixinBlockVine {

    @Inject(method = "neighborChanged", at = @At(value = "HEAD"), cancellable = true)
    public void onNeighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, CallbackInfo ci) {
        if (WorldGuardConfig.plants.canPlantsPlacedEverywhere) {
            ci.cancel();
        }
    }

    @Inject(method = "updateTick", at = @At(value = "HEAD"), cancellable = true)
    public void onUpdateTick(World worldIn, BlockPos pos, IBlockState state, Random rand, CallbackInfo ci) {
        if (!WorldGuardConfig.plants.canVineTick) {
            ci.cancel();
        }
    }

}
