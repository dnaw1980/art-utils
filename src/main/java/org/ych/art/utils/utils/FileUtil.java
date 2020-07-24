package org.ych.art.utils.utils;

import java.io.File;

/**
 * 文件工具类
 */
public class FileUtil {

    /**
     * 创建目录
     *
     * @param path
     */
    public static File mkdirs(String path) {
        File dir = new File(path);
        // 判断文件目录是否存在
        if (!dir.exists() && !dir.isDirectory()) {
            //自动创建多级目录
            dir.mkdirs();
        }

        return dir;
    }

}
