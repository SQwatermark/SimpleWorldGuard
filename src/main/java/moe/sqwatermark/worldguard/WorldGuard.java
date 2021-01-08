package moe.sqwatermark.worldguard;

import moe.sqwatermark.config.WorldGuardConfig;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber
@Mod(modid = WorldGuard.MOD_ID, name = WorldGuard.MOD_NAME, version = WorldGuard.MOD_VERSION, guiFactory = "moe.sqwatermark.config.GuiFactory")
public class WorldGuard {

    public static final String MOD_ID = "worldguard";
    public static final String MOD_NAME = "WorldGuard";
    public static final String MOD_VERSION = "@VERSION@";
    public static final Logger LOGGER = LogManager.getLogger();

    //TODO 阻止火传播 阻止水流动
    //TODO 阻止闪电点燃方块 阻止岩浆传播火 阻止水和岩浆合成石头
    //TODO 阻止苦力怕/TNT爆炸 阻止爆炸破坏方块
    //TODO 允许部分方块放在任意地方（花草、红石等）
    //TODO 阻止藤蔓蔓延
    //TODO 箱子保护

    //TODO 高级计划：在某个区域内设置保护

    // 阻止作物生长
    @SubscribeEvent
    public static void handleCropGrow(BlockEvent.CropGrowEvent.Pre event) {
        if (!WorldGuardConfig.canCropGrow) {
            event.setResult(Event.Result.DENY);
        }
    }

    // 阻止农田踩踏
    @SubscribeEvent
    public static void handleFarmlandTrample(BlockEvent.FarmlandTrampleEvent event) {
        if (!WorldGuardConfig.canFarmlandTrample) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void handleEntityMobGriefing(EntityMobGriefingEvent event) {
        // 阻止末影人搬走或放置方块
        if (event.getEntity() instanceof EntityEnderman && !WorldGuardConfig.canEndermanMoveBlock) event.setResult(Event.Result.DENY);
        else if (event.getEntity() instanceof EntitySnowman && !WorldGuardConfig.canSnowmanSpawnSnow) event.setResult(Event.Result.DENY);
    }

    // 阻止鸡蛋生成鸡
    @SubscribeEvent
    public static void dontSpawnChicken(ProjectileImpactEvent.Throwable event) {
        EntityThrowable throwable = event.getThrowable();
        if (throwable instanceof EntityEgg && !WorldGuardConfig.canEggSpawnChicken) {
            event.setCanceled(true);
            if (event.getRayTraceResult().entityHit != null) {
                event.getRayTraceResult().entityHit.attackEntityFrom(DamageSource.causeThrownDamage(throwable, throwable.getThrower()), 0.0F);
            }
            if (!throwable.world.isRemote) {
                throwable.world.setEntityState(throwable, (byte)3);
                throwable.setDead();
            }
        }
    }

    @SubscribeEvent
    public static void onExplosion(ExplosionEvent event) {
        // 防止爆炸破坏方块
        if (!WorldGuardConfig.canExplosionDamageBlock) {
            event.getExplosion().clearAffectedBlockPositions();
        }
    }

    /**
     * 同步配置文件
     */
    @Mod.EventBusSubscriber(modid = MOD_ID)
    public static class ConfigSyncHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(MOD_ID)) {
                ConfigManager.sync(MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}