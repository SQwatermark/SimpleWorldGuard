package moe.sqwatermark.worldguard.mixins;

import moe.sqwatermark.config.WorldGuardConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public class MixinWorld {

    @Inject(method = "canBlockFreezeNoWater", at = @At(value = "HEAD"), cancellable = true)
    public void canBlockFreezeNoWater(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!WorldGuardConfig.canWaterFreeze) {
            cir.setReturnValue(false);
        }
    }

}
