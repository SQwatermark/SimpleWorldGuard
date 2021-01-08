package moe.sqwatermark.config;

import moe.sqwatermark.worldguard.WorldGuard;
import net.minecraftforge.common.config.Config;

@Config(modid = WorldGuard.MOD_ID)
public final class WorldGuardConfig {

    //*************************** 农田 ****************************//

    @Config.Comment("农作物是否会生长")
    @Config.LangKey("config.worldguard.farm.canCropGrow")
    public static boolean canCropGrow = true;

    @Config.Comment("农田是否会被踩坏")
    @Config.LangKey("config.worldguard.farm.canFarmlandTrample")
    public static boolean canFarmlandTrample = true;

    @Config.Comment("农田是否会更新随机刻")
    @Config.LangKey("config.worldguard.farm.canFarmlandTick")
    public static boolean canFarmlandTick = true;

    @Config.Comment("农田是否会干涸，前置条件为canFarmlandTick = true，此选项仅阻止农田因为缺水而变成泥土，想让你的农作物旺盛成长，还是需要水的")
    @Config.LangKey("config.worldguard.farm.canFarmlandDry")
    public static boolean canFarmlandDry = true;

    //*************************** 植被 ****************************//

    @Config.Comment("树叶是否会更新随机刻，设为false可防止树叶掉落")
    @Config.LangKey("config.worldguard.plants.canLeavesTick")
    public static boolean canLeavesTick = true;

    //*************************** 地面 ****************************//
    @Config.Comment("冰块和霜冰是否会更新随机刻，设为false可防止冰融化")
    @Config.LangKey("config.worldguard.earth.canIceTick")
    public static boolean canIceTick = true;

    @Config.Comment("雪片和雪方块是否会更新随机刻，设为false可防止雪融化")
    @Config.LangKey("config.worldguard.earth.canSnowTick")
    public static boolean canSnowTick = true;

    @Config.Comment("水是否会结冰")
    @Config.LangKey("config.worldguard.earth.canWaterFreeze")
    public static boolean canWaterFreeze = true;

    @Config.Comment("雪天是否会生成雪片")
    @Config.LangKey("config.worldguard.earth.canSnowSpawn")
    public static boolean canSnowSpawn = true;

    @Config.Comment("沙子/红沙是否会下落")
    @Config.LangKey("config.worldguard.earth.canSandFall")
    public static boolean canSandFall = true;

    @Config.Comment("混凝土粉末是否会下落")
    @Config.LangKey("config.worldguard.earth.canConcretePowderFall")
    public static boolean canConcretePowderFall = true;

    @Config.Comment("砂砾是否会下落")
    @Config.LangKey("config.worldguard.earth.canGravelFall")
    public static boolean canGravelFall = true;

    //*************************** 生物 ****************************//

    @Config.Comment("末影人是否会搬走或者放置方块")
    @Config.LangKey("config.worldguard.entity.canEndermanMoveBlock")
    public static boolean canEndermanMoveBlock = true;

    @Config.Comment("雪人是否产生雪")
    @Config.LangKey("config.worldguard.entity.canSnowmanSpawnSnow")
    public static boolean canSnowmanSpawnSnow = true;

    //*************************** 杂项 ****************************//
    @Config.Comment("末影人是否会搬走或者放置方块")
    @Config.LangKey("config.worldguard.misc.canEggSpawnChicken")
    public static boolean canEggSpawnChicken = true;

}