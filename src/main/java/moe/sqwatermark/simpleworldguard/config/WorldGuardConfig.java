package moe.sqwatermark.simpleworldguard.config;

import moe.sqwatermark.simpleworldguard.WorldGuard;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = WorldGuard.MOD_ID)
public final class WorldGuardConfig {

    @Config.Comment("农田设置")
    @Config.LangKey("config.simpleworldguard.farm")
    public static FarmCategory farm = new FarmCategory();

    @Config.Comment("植物设置")
    @Config.LangKey("config.simpleworldguard.plants")
    public static PlantsCategory plants = new PlantsCategory();

    @Config.Comment("水、雪、冰设置")
    @Config.LangKey("config.simpleworldguard.h2o")
    public static H2OCategory h2o = new H2OCategory();

    @Config.Comment("沙子、混凝土粉末等重力方块的设置")
    @Config.LangKey("config.simpleworldguard.physics")
    public static PhysicsCategory physics = new PhysicsCategory();

    @Config.Comment("实体行为设置")
    @Config.LangKey("config.simpleworldguard.entity")
    public static EntityCategory entity = new EntityCategory();

    @Config.Comment("爆炸保护设置")
    @Config.LangKey("config.simpleworldguard.explosion")
    public static ExplosionCategory explosion = new ExplosionCategory();

    @Config.Comment("火焰保护设置")
    @Config.LangKey("config.simpleworldguard.fire")
    public static FireCategory fire = new FireCategory();

    @Config.Comment("自定义游戏标题")
    @Config.LangKey("config.simpleworldguard.customGameTitle")
    public static String customGameTitle = "";

    public static class FarmCategory {
        @Config.Comment("农作物是否会生长")
        @Config.LangKey("config.simpleworldguard.farm.canCropGrow")
        public boolean canCropGrow = true;

        @Config.Comment("农田是否会被踩坏")
        @Config.LangKey("config.simpleworldguard.farm.canFarmlandTrample")
        public boolean canFarmlandTrample = true;

        @Config.Comment("农田是否会更新随机刻")
        @Config.LangKey("config.simpleworldguard.farm.canFarmlandTick")
        public boolean canFarmlandTick = true;

        @Config.Comment("农田是否会干涸，前置条件为农田随机刻，此选项仅阻止农田因为缺水而变成泥土，想让你的农作物旺盛成长，还是需要水的")
        @Config.LangKey("config.simpleworldguard.farm.canFarmlandDry")
        public boolean canFarmlandDry = true;
    }

    public static class PlantsCategory {
        @Config.Comment("树叶是否会更新随机刻，设为false可防止树叶掉落")
        @Config.LangKey("config.simpleworldguard.plants.canLeavesTick")
        public boolean canLeavesTick = true;

        @Config.Comment("草方块是否会扩散")
        @Config.LangKey("config.simpleworldguard.plants.canGrassSpread")
        public boolean canGrassSpread = true;

        @Config.Comment("菌丝是否会扩散")
        @Config.LangKey("config.simpleworldguard.plants.canMyceliumSpread")
        public boolean canMyceliumSpread = true;

        @Config.Comment("藤蔓是否会更新随机刻，设为false可防止藤蔓生长")
        @Config.LangKey("config.simpleworldguard.plants.canVineTick")
        public boolean canVineTick = true;

        // TODO 还没很好地实现
        @Config.Comment("植物（藤蔓）是否不需要附着物(还没很好地实现，不要设置这个)")
        @Config.LangKey("config.simpleworldguard.plants.canPlantsPlacedEverywhere")
        public boolean canPlantsPlacedEverywhere = false;
    }

    public static class H2OCategory {
        @Config.Comment("冰块和霜冰是否会更新随机刻，设为false可防止冰融化")
        @Config.LangKey("config.simpleworldguard.h2o.canIceTick")
        public boolean canIceTick = true;

        @Config.Comment("雪片和雪方块是否会更新随机刻，设为false可防止雪融化")
        @Config.LangKey("config.simpleworldguard.h2o.canSnowTick")
        public boolean canSnowTick = true;

        @Config.Comment("水是否会结冰")
        @Config.LangKey("config.simpleworldguard.h2o.canWaterFreeze")
        public boolean canWaterFreeze = true;

        @Config.Comment("雪天是否会产生积雪")
        @Config.LangKey("config.simpleworldguard.h2o.canSnowSpawn")
        public boolean canSnowSpawn = true;
    }

    public static class PhysicsCategory {
        @Config.Comment("沙子/红沙是否会下落")
        @Config.LangKey("config.simpleworldguard.physics.canSandFall")
        public boolean canSandFall = true;

        @Config.Comment("混凝土粉末是否会下落")
        @Config.LangKey("config.simpleworldguard.physics.canConcretePowderFall")
        public boolean canConcretePowderFall = true;

        @Config.Comment("砂砾是否会下落")
        @Config.LangKey("config.simpleworldguard.physics.canGravelFall")
        public boolean canGravelFall = true;
    }

    public static class EntityCategory {
        @Config.Comment("末影人是否会搬走或者放置方块")
        @Config.LangKey("config.simpleworldguard.entity.canEndermanMoveBlock")
        public boolean canEndermanMoveBlock = true;

        @Config.Comment("雪人是否产生雪")
        @Config.LangKey("config.simpleworldguard.entity.canSnowmanSpawnSnow")
        public boolean canSnowmanSpawnSnow = true;
    }

    public static class ExplosionCategory {
        @Config.Comment("爆炸是否会破坏方块，如果设置为false，所有方块都会被保护")
        @Config.LangKey("config.simpleworldguard.explosion.canExplosionDamageBlock")
        public boolean canExplosionDamageBlock = true;

        @Config.Comment("不会受到爆炸影响的方块列表，请填写带有命名空间的完整id")
        @Config.LangKey("config.simpleworldguard.explosion.explosionProtectedBlocks")
        public String[] explosionProtectedBlocks = new String[] { "minecraft:chest", "minecraft:bed" };
    }

    public static class FireCategory {
        @Config.Comment("在doFireTick为false的情况下，火焰也会自动熄灭（不会影响灵魂沙）")
        @Config.LangKey("config.simpleworldguard.explosion.fireAutoExtinguish")
        public boolean fireAutoExtinguish = false;

//        TODO 附加火焰保护的方块
//        @Config.Comment("不会受到爆炸影响的方块列表，请填写带有命名空间的完整id")
//        @Config.LangKey("config.simpleworldguard.explosion.explosionProtectedBlocks")
//        public String[] explosionProtectedBlocks = new String[] { "minecraft:chest", "minecraft:bed" };
    }

    /**
     * 同步配置文件
     */
    @Mod.EventBusSubscriber(modid = WorldGuard.MOD_ID)
    public static class ConfigSyncHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            System.out.println("重载配置文件");
            if (event.getModID().equals(WorldGuard.MOD_ID)) {
                ConfigManager.sync(WorldGuard.MOD_ID, Config.Type.INSTANCE);
            }
            WorldGuard.proxy.handleConfigChange();
        }
    }

}