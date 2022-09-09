package com.example.making.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class FileUtils {

    /*
     * 设置抓图保存路径
     */
    public static String getSaveCapturePath(String eqSn) {
        File path1 = new File("C:\\Users\\Administrator\\Desktop\\tupian\\");
        if (!path1.exists()) {
            path1.mkdir();
        }

        File path2 = new File("C:\\Users\\Administrator\\Desktop\\tupian\\");
        if (!path2.exists()) {
            path2.mkdir();
        }

        String strFileName = path2.getAbsolutePath() + "/" + eqSn + ".jpg";

        return strFileName;
    }

    public static String upload(MultipartFile file, String path, String fileName) throws Exception {
        // 生成新的文件名
        String realPath = path + "/" + UUID.randomUUID().toString().replace("-", "")+fileName.substring(fileName.lastIndexOf("."));
        File dest = new File(realPath);
        // 判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        // 保存文件
        file.transferTo(dest);
        return dest.getName();
    }

}
