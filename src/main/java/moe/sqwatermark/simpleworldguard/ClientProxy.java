package moe.sqwatermark.simpleworldguard;

import moe.sqwatermark.simpleworldguard.client.util.IconLoader;
import moe.sqwatermark.simpleworldguard.config.WorldGuardConfig;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.opengl.Display;

import java.io.File;
import java.nio.file.Paths;

/**
 * @author SQwatermark
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        this.handleConfigChange();
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
        if (WorldGuard.modConfigDi.exists()) {
            File icon = Paths.get(WorldGuard.modConfigDi.getAbsolutePath(), "icon.png").toFile();
            if(!icon.exists()) WorldGuard.logger.info("未找到游戏图标");
            else if(!icon.isDirectory()) Display.setIcon(IconLoader.load(icon));
        } else {
            WorldGuard.logger.error("已生成模组配置文件目录");
            if (WorldGuard.modConfigDi.mkdir()) {
                WorldGuard.logger.info("已生成模组配置文件目录");
            }
        }
    }

    public void handleConfigChange() {
        if (!WorldGuardConfig.customGameTitle.isEmpty()) {
            Display.setTitle(WorldGuardConfig.customGameTitle);
        }
        this.loadCustomIcon();
    }

}
