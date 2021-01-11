package moe.sqwatermark.simpleworldguard;

import moe.sqwatermark.simpleworldguard.client.SplashLoader;
import moe.sqwatermark.simpleworldguard.client.util.IconLoader;
import moe.sqwatermark.simpleworldguard.config.WorldGuardConfig;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.opengl.Display;

import java.io.File;
import java.nio.file.Paths;

/**
 * @author SQwatermark
 */
@Mod.EventBusSubscriber(modid = WorldGuard.MOD_ID, value = Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        this.handleConfigChange();
        this.loadCustomIcon();
        this.loadCustomSplash();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    /**
     * 读取自定义的游戏图标
     */
    private void loadCustomIcon() {
        if (WorldGuard.getModConfigDi().exists()) {
            File icon = Paths.get(WorldGuard.getModConfigDi().getAbsolutePath(), "icon.png").toFile();
            if(!icon.exists()) WorldGuard.logger.info("未找到游戏图标");
            else if(!icon.isDirectory()) Display.setIcon(IconLoader.load(icon));
        } else {
            WorldGuard.logger.warn("模组配置文件目录不存在");
            if (WorldGuard.getModConfigDi().mkdir()) {
                WorldGuard.logger.info("已生成模组配置文件目录");
            }
        }
    }

    private void loadCustomSplash() {
        if (WorldGuard.getModConfigDi().exists()) {
            File customsplash = Paths.get(WorldGuard.getModConfigDi().getAbsolutePath(), "customsplash.txt").toFile();
            if(!customsplash.exists()) WorldGuard.logger.info("未找到自定义splash");
            else if(!customsplash.isDirectory()) {
                SplashLoader.parseCustomSplash(customsplash);
            }
        } else {
            WorldGuard.logger.warn("模组配置文件目录不存在");
            if (WorldGuard.getModConfigDi().mkdir()) {
                WorldGuard.logger.info("已生成模组配置文件目录");
            }
        }
    }

    public void handleConfigChange() {
        if (!WorldGuardConfig.customGameTitle.isEmpty()) {
            Display.setTitle(WorldGuardConfig.customGameTitle);
        }
    }

    @SubscribeEvent
    public static void replaceSplashText(GuiScreenEvent.InitGuiEvent event) {
        if (event.getGui() instanceof GuiMainMenu) {
            GuiMainMenu guiMainMenu = (GuiMainMenu) event.getGui();
            if (!SplashLoader.getSplash().isEmpty()) {
                guiMainMenu.splashText = SplashLoader.getSplash();
            }
        }
    }
}
