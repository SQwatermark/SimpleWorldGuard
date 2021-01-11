package moe.sqwatermark.simpleworldguard.mixins.world;

import moe.sqwatermark.simpleworldguard.config.WorldGuardConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldServer.class)
public class MixinWorldServer {

    // canSnowAt方法还有别的用处，不能直接修改
    @Redirect(
            method = "updateBlocks",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldServer;canSnowAt(Lnet/minecraft/util/math/BlockPos;Z)Z")
    )
    private boolean redirect_canSnowAt(WorldServer worldServer, BlockPos pos, boolean checkLight) {
        if (WorldGuardConfig.h2o.canSnowSpawn) {
            return worldServer.canSnowAt(pos, checkLight);
        } else {
            // 阻止雪天积雪
            return false;
        }
    }

}
