package moe.sqwatermark.simpleworldguard.mixins.block;

import moe.sqwatermark.simpleworldguard.WorldGuard;
import moe.sqwatermark.simpleworldguard.config.WorldGuardConfig;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockSoulSand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BlockFire.class)
public class MixinBlockFire {

    @Inject(method = "updateTick", at = @At("HEAD"), cancellable = true)
    void inject_updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand, CallbackInfo ci) {
        if (WorldGuardConfig.fire.fireAutoExtinguish) {
            if (!(worldIn.getBlockState(pos.down()).getBlock() instanceof BlockSoulSand)) {
                worldIn.setBlockToAir(pos);
            }
        }
    }

}
