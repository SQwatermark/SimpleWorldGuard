package moe.sqwatermark.worldguard.mixins;

import moe.sqwatermark.config.WorldGuardConfig;
import net.minecraft.block.BlockIce;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BlockIce.class)
public class MixinBlockIce {

    @Inject(method = "updateTick", at = @At(value = "HEAD"), cancellable = true)
    public void onUpdateTick(World worldIn, BlockPos pos, IBlockState state, Random rand, CallbackInfo ci) {
        if (!WorldGuardConfig.h2o.canIceTick) {
            ci.cancel();
        }
    }

}
