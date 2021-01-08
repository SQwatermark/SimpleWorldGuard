package moe.sqwatermark.worldguard.mixins;

import moe.sqwatermark.config.WorldGuardConfig;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BlockMycelium.class)
public class MixinBlockMycelium {

    // 或许得用一种更好的方法来判断
    @Inject(method = "updateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;)Z", ordinal = 1), cancellable = true)
    public void onUpdateTick2(World worldIn, BlockPos pos, IBlockState state, Random rand, CallbackInfo ci) {
        if (!WorldGuardConfig.canMyceliumSpread) {
            ci.cancel();
        }
    }

}
