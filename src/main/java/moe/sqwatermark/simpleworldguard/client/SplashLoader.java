package moe.sqwatermark.simpleworldguard.client;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * 解析customsplash.txt文件
 */
public class SplashLoader {

    private static String splash = "";

    public static void parseCustomSplash(File customSplash) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        // TODO 用正则表达式来判断吧
        splash = "";
    }

    public static String getSplash() {
        return splash;
    }

}