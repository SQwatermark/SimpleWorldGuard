package moe.sqwatermark.simpleworldguard;

import moe.sqwatermark.simpleworldguard.config.WorldGuardConfig;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @author SQwatermark
 */
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
    public static Logger logger;

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
    //TODO 生物黑名单，禁止指定生物的生成
    //TODO 让火不能点燃方块：https://www.mcbbs.net/thread-1151688-1-1.html doFireTick只会阻止火焰蔓延而不会灭火

    @SidedProxy(clientSide = "moe.sqwatermark.simpleworldguard.ClientProxy",
            serverSide = "moe.sqwatermark.simpleworldguard.CommonProxy")
    public static CommonProxy proxy;

    // 配置文件目录（.minecraft/config/simpleworldguard）
    private static File modConfigDi;

    public static File getModConfigDi() {
        return modConfigDi;
    }

    // 默认的配置文件是在preInit之前读取的所以可以放心使用
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        modConfigDi = Paths.get(event.getModConfigurationDirectory().getAbsolutePath(), "simpleworldguard").toFile();
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }


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
}