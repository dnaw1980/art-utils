/**   
* @Title: GzipUtils.java 
* @Package com.futvan.util.file 
* @Description: TODO(用一句话描述该文件做什么) 
* @author Joy che13898562885@163.com  
* @date 2018年3月21日 上午11:47:42 
* @version V2.0.3   
*/
package org.ych.art.utils.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/** 
* @ClassName: GzipUtils 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Joy che13898562885@163.com
* @date 2018年3月21日 上午11:47:42 
*  
*/
public class GzipUtils {
	
    private static final int BUFFER = 1024;

    /**
     * 
    * @Title: compress 
    * @Description: TODO("压缩成GZIP文件") 
    * @param @param orginalFile
    * @param @param compressFile
    * @param @throws IOException    设定文件 
    * @return void    返回类型 
    * @throws 
     */
    public static void compress(File orginalFile, File compressFile) throws IOException {
        if (orginalFile != null && orginalFile.isFile() && compressFile != null && compressFile.isFile()) {
            FileInputStream is = new FileInputStream(orginalFile);
            FileOutputStream os = new FileOutputStream(compressFile);
            compress(is, os);
            is.close();
            os.close();
        }
    }

    public static void compress(String orginalFile, String compressFile) throws IOException {
        if (orginalFile != null && compressFile != null) {
            FileInputStream is = new FileInputStream(orginalFile);
            FileOutputStream os = new FileOutputStream(compressFile);
            compress(is, os);
            is.close();
            os.close();
        }
    }

    public static byte[] compress(byte[] bytes) throws IOException {
        if (bytes != null) {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            compress(bais, baos);
            bytes = baos.toByteArray();
            bais.close();
            baos.close();
        }
        return bytes;
    }

    public static void compress(InputStream is, OutputStream os) throws IOException {
        GZIPOutputStream gos = new GZIPOutputStream(os);
        int len = 0;
        byte[] buffer = new byte[BUFFER];
        while ((len = is.read(buffer)) != -1) {
            gos.write(buffer, 0, len);
        }
        gos.finish();
        gos.flush();
        gos.close();
    }

    /**
     * 
    * @Title: deCompress 
    * @Description: TODO("解压成GZIP文件") 
    * @param @param compressFile
    * @param @param orginalFile
    * @param @throws IOException    设定文件 
    * @return void    返回类型 
    * @throws 
     */
    public static void deCompress(File compressFile, File orginalFile) throws IOException {
        if (orginalFile != null && orginalFile.isFile() && compressFile != null && compressFile.isFile()) {
            FileInputStream is = new FileInputStream(compressFile);
            FileOutputStream os = new FileOutputStream(orginalFile);
            deCompress(is, os);
            is.close();
            os.close();
        }
    }

    public static void deCompress(String compressFile, String orginalFile) throws IOException {
        if (orginalFile != null && compressFile != null) {
            FileInputStream is = new FileInputStream(compressFile);
            FileOutputStream os = new FileOutputStream(orginalFile);
            deCompress(is, os);
            is.close();
            os.close();
        }
    }

    public static byte[] deCompress(byte[] bytes) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        deCompress(bais, baos);
        bytes = baos.toByteArray();
        bais.close();
        baos.close();
        return bytes;
    }


    public static void deCompress(InputStream is, OutputStream os) throws IOException {
        GZIPInputStream gis = new GZIPInputStream(is);
        int len = 0;
        byte[] buffer = new byte[BUFFER];
        while ((len = gis.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        os.flush();
        gis.close();
    }
    
//    public static void main(String[] args) throws IOException {
//        fileTest();
//    }
//    
//    public static void fileTest() throws IOException {
//        String orginal = "E:\\demo\\CUSTBALCHK_T000002_20170617_001.txt";
//        String compress = "E:\\demo\\CUSTBALCHK_T000002_20170617_001.txt.gz";
////        String decompress = "E:\\demo\\1(1).txt";
//
//        File orginalFile = new File(orginal);
//        File compressFile = new File(compress);
////        File decompressFile = new File(decompress);
//        if (!compressFile.exists()) {
//            compressFile.createNewFile();
//        }
//        GzipUtils.compress(orginalFile, compressFile);
//    }

	

}
