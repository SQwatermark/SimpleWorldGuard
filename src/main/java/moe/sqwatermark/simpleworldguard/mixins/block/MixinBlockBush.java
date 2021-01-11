package moe.sqwatermark.simpleworldguard.mixins.block;

import moe.sqwatermark.simpleworldguard.config.WorldGuardConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockBush.class)
public class MixinBlockBush {

    @Inject(method = "neighborChanged", at = @At("HEAD"), cancellable = true)
    public void onNeighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, CallbackInfo ci) {
        if (WorldGuardConfig.plants.canPlantsPlacedEverywhere) {
            ci.cancel();
        }
    }

}
