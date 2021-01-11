package moe.sqwatermark.simpleworldguard.mixins.block;

import moe.sqwatermark.simpleworldguard.config.WorldGuardConfig;
import net.minecraft.block.BlockFrostedIce;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BlockFrostedIce.class)
public class MixinBlockFrostedIce {

    @Inject(method = "updateTick", at = @At(value = "HEAD"), cancellable = true)
    public void onUpdateTick(World worldIn, BlockPos pos, IBlockState state, Random rand, CallbackInfo ci) {
        if (!WorldGuardConfig.h2o.canIceTick) {
            ci.cancel();
        }
    }

}
