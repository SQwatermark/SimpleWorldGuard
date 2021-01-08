package moe.sqwatermark.worldguard.mixins;

import moe.sqwatermark.worldguard.WorldGuard;
import moe.sqwatermark.config.WorldGuardConfig;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BlockFarmland.class)
public class MixinBlockFarmland {

    @Inject(method = "updateTick", at = @At("HEAD"), cancellable = true)
    public void onUpdateTick(World worldIn, BlockPos pos, IBlockState state, Random rand, CallbackInfo ci) {
        if (!WorldGuardConfig.canFarmlandTick) {
            WorldGuard.LOGGER.info("取消农田tick");
            ci.cancel();
        }
    }

    @Inject(method = "updateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockFarmland;turnToDirt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V"), cancellable = true)
    public void onFarmlandDry(World worldIn, BlockPos pos, IBlockState state, Random rand, CallbackInfo ci) {
        if (!WorldGuardConfig.canFarmlandDry) {
            WorldGuard.LOGGER.info("防止农田干涸");
            ci.cancel();
        }
    }

}
