package moe.sqwatermark.simpleworldguard.mixins;

import moe.sqwatermark.simpleworldguard.config.WorldGuardConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public class MixinWorld {

    // 阻止水结冰
    @Inject(method = "canBlockFreezeNoWater", at = @At(value = "HEAD"), cancellable = true)
    public void canBlockFreezeNoWater(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!WorldGuardConfig.h2o.canWaterFreeze) {
            cir.setReturnValue(false);
        }
    }

}
