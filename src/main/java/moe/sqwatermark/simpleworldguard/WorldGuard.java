package moe.sqwatermark.simpleworldguard;

import moe.sqwatermark.simpleworldguard.config.WorldGuardConfig;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

@Mod.EventBusSubscriber
@Mod(
        modid = WorldGuard.MOD_ID,
        name = WorldGuard.MOD_NAME,
        version = WorldGuard.MOD_VERSION,
        acceptedMinecraftVersions = "[1.12.2]",
        acceptableRemoteVersions = "*",
        guiFactory = "moe.sqwatermark.simpleworldguard.config.GuiFactory")
public class WorldGuard {

    public static final String MOD_ID = "simpleworldguard";
    public static final String MOD_NAME = "SimpleWorldGuard";
    public static final String MOD_VERSION = "@VERSION@";
    public static final Logger LOGGER = LogManager.getLogger();

    /*
        一些原版的设置，就不用画蛇添足了
        doFireTick  火是否蔓延及自然熄灭。
        doMobSpawning   生物是否自然生成。不影响刷怪笼。
        tntexplodes     TNT是否会爆炸。
        mobGriefing     生物是否能够进行破坏性行为
        doWeatherCycle      天气是否变化
     */

    //TODO 阻止水流动
    //TODO 阻止水和岩浆合成石头
    //TODO 允许部分方块放在任意地方（花草、红石等）

    // 阻止作物生长
    @SubscribeEvent
    public static void handleCropGrow(BlockEvent.CropGrowEvent.Pre event) {
        if (!WorldGuardConfig.farm.canCropGrow) {
            event.setResult(Event.Result.DENY);
        }
    }

    // 阻止农田踩踏
    @SubscribeEvent
    public static void handleFarmlandTrample(BlockEvent.FarmlandTrampleEvent event) {
        if (!WorldGuardConfig.farm.canFarmlandTrample) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void handleEntityMobGriefing(EntityMobGriefingEvent event) {
        // 阻止末影人搬走或放置方块
        if (event.getEntity() instanceof EntityEnderman && !WorldGuardConfig.entity.canEndermanMoveBlock) event.setResult(Event.Result.DENY);
        else if (event.getEntity() instanceof EntitySnowman && !WorldGuardConfig.entity.canSnowmanSpawnSnow) event.setResult(Event.Result.DENY);
    }

    @SubscribeEvent
    public static void onExplosion(ExplosionEvent event) {
        // 防止爆炸破坏方块
        if (!WorldGuardConfig.explosion.canExplosionDamageBlock) {
            event.getExplosion().clearAffectedBlockPositions();
        } else {
            // 排除被保护的方块
            for (String explosionProtectedBlock : WorldGuardConfig.explosion.explosionProtectedBlocks) {
                event.getExplosion().getAffectedBlockPositions().removeIf(blockPos ->
                        Objects.requireNonNull(
                                event.getWorld().getBlockState(blockPos).getBlock().getRegistryName()).toString().equals(explosionProtectedBlock)
                );
            }
        }
    }

    /**
     * 同步配置文件
     */
    @Mod.EventBusSubscriber(modid = MOD_ID, value = Side.CLIENT)
    public static class ConfigSyncHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(MOD_ID)) {
                ConfigManager.sync(MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}