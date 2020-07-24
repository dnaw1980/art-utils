package org.ych.art.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.tools.bzip2.CBZip2InputStream;
import org.apache.tools.bzip2.CBZip2OutputStream;

import com.jcraft.jzlib.JZlib;
import com.jcraft.jzlib.ZInputStream;
import com.jcraft.jzlib.ZOutputStream;

public class DataParse {
    /***
     * 压缩GZip，性能好，建议使用
     *
     * @param data
     * @return
     */
    public static byte[] gZip(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(bos);
            gzip.write(data);
            gzip.finish();
            gzip.close();
            b = bos.toByteArray();
            bos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    /***
     * 解压GZip，性能好，建议使用
     *
     * @param data
     * @return
     */
    public static byte[] unGZip(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            GZIPInputStream gzip = new GZIPInputStream(bis);
            byte[] buf = new byte[1024];
            int num = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((num = gzip.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            b = baos.toByteArray();
            baos.flush();
            baos.close();
            gzip.close();
            bis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    /***
     * 压缩Zip
     *
     * @param data
     * @return
     */
    public static byte[] zip(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ZipOutputStream zip = new ZipOutputStream(bos);
            ZipEntry entry = new ZipEntry("zip");
            entry.setSize(data.length);
            zip.putNextEntry(entry);
            zip.write(data);
            zip.closeEntry();
            zip.close();
            b = bos.toByteArray();
            bos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    /***
     * 解压Zip
     *
     * @param data
     * @return
     */
    public static byte[] unZip(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            ZipInputStream zip = new ZipInputStream(bis);
            while (zip.getNextEntry() != null) {
                byte[] buf = new byte[1024];
                int num = -1;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ((num = zip.read(buf, 0, buf.length)) != -1) {
                    baos.write(buf, 0, num);
                }
                b = baos.toByteArray();
                baos.flush();
                baos.close();
            }
            zip.close();
            bis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    /***
     * 压缩BZip2
     *
     * @param data
     * @return
     */
    public static byte[] bZip2(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            CBZip2OutputStream bzip2 = new CBZip2OutputStream(bos);
            bzip2.write(data);
            bzip2.flush();
            bzip2.close();
            b = bos.toByteArray();
            bos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    /***
     * 解压BZip2
     *
     * @param data
     * @return
     */
    public static byte[] unBZip2(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            CBZip2InputStream bzip2 = new CBZip2InputStream(bis);
            byte[] buf = new byte[1024];
            int num = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((num = bzip2.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            b = baos.toByteArray();
            baos.flush();
            baos.close();
            bzip2.close();
            bis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    /**
     * 把字节数组转换成16进制字符串
     *
     * @param bArray
     * @return
     */
    public static String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * jzlib 压缩数据，过时，不建议使用
     *
     * @param object
     * @return
     * @throws IOException
     */
    @Deprecated
    public static byte[] jzlib(byte[] object) {
        byte[] data = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ZOutputStream zOut = new ZOutputStream(out, JZlib.Z_DEFAULT_COMPRESSION);
            DataOutputStream objOut = new DataOutputStream(zOut);
            objOut.write(object);
            objOut.flush();
            zOut.close();
            data = out.toByteArray();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * jzLib压缩的数据，过时，不建议使用
     *
     * @param object
     * @return
     * @throws IOException
     */
    @Deprecated
    public static byte[] unjzlib(byte[] object) {
        byte[] data = null;
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(object);
            ZInputStream zIn = new ZInputStream(in);
            byte[] buf = new byte[1024];
            int num = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((num = zIn.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            data = baos.toByteArray();
            baos.flush();
            baos.close();
            zIn.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {

        final int times = 10000;
        String s = "this is a test";

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 100; i++) {
            sb.append(s);
        }

        s = sb.toString();

        System.out.println(s);
        System.out.println("原串长：" + s.length());
        byte[] b = s.getBytes();
        System.out.println("原字节长：" + b.length);

        // ZIP压缩法

        System.out.println("ZIP压缩法");
        byte[] b1 = zip(b);
        System.out.println("压缩后长 len:\t" + b1.length);
        System.out.println("压缩串     :\t" + bytesToHexString(b1));
        byte[] b2 = unZip(b1);
        System.out.println("解压后长 len:\t" + b2.length);
        System.out.println("解压串      :\t" + new String(b2));

        System.out.println("压缩比：\t" + (((float) b1.length) / b2.length));

        long begin = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            unZip(zip(b));
        }
        long end = System.currentTimeMillis();
        System.out.println("循环" + times + "用时：" + (end - begin));

        System.out.println("================================================");

        // bZip2压缩法
        System.out.println("bZip2压缩法");
        byte[] b3 = bZip2(b);
        System.out.println("压缩后长 len:\t" + b3.length);
        System.out.println("压缩串     :\t" + bytesToHexString(b3));
        byte[] b4 = unBZip2(b3);
        System.out.println("解压后长 len:\t" + b4.length);
        System.out.println("解压串      :\t" + new String(b4));

        System.out.println("压缩比：\t" + (((float) b3.length) / b4.length));

        begin = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            unBZip2(bZip2(b));
        }
        end = System.currentTimeMillis();
        System.out.println("循环" + times + "用时：" + (end - begin));

        System.out.println("================================================");

        // gZip压缩法 -- 综合性能第一
        System.out.println("gZip压缩法");
        byte[] b5 = gZip(b);
        System.out.println("压缩后长 len:\t" + b5.length);
        System.out.println("压缩串     :\t" + bytesToHexString(b5));
        byte[] b6 = unGZip(b5);
        System.out.println("解压后长 len:\t" + b6.length);
        System.out.println("解压串      :\t" + new String(b6));

        System.out.println("压缩比：\t" + (((float) b5.length) / b6.length));

        begin = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            unGZip(gZip(b));
        }
        end = System.currentTimeMillis();
        System.out.println("循环" + times + "用时：" + (end - begin));

        System.out.println("================================================");

        // jzlib压缩法
        System.out.println("jzlib压缩法");
        byte[] b7 = jzlib(b);
        System.out.println("压缩后长 len:\t" + b7.length);
        System.out.println("压缩串     :\t" + bytesToHexString(b7));
        byte[] b8 = unjzlib(b7);
        System.out.println("解压后长 len:\t" + b8.length);
        System.out.println("解压串      :\t" + new String(b8));

        System.out.println("压缩比：\t" + (((float) b7.length) / b8.length));

        begin = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            unjzlib(jzlib(b));
        }
        end = System.currentTimeMillis();
        System.out.println("循环" + times + "用时：" + (end - begin));

    }

}
