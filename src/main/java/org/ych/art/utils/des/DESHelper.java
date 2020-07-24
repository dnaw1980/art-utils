package org.ych.art.utils.des;

import org.ych.art.utils.utils.Tools;

import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


/**
 * DES算法加密工具类
 *
 * @author 姚赪海
 */
public class DESHelper {

    /**
     * 加密格式
     */
    public static final String DES = "DES";

    /**
     * 母串
     */
    private static final char[] CHAR_ARR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
            .toCharArray();

    /**
     * 母串长度
     */
    private static final int CHAR_ARR_LEN = CHAR_ARR.length;

    /**
     * 每次生成随机密码的基本密码
     */
    private static String rootKey = "";

    /**
     * 密码长度
     */
    private static final int PWD_LEN = 56;

    static {
        Random random = new Random();
        char[] charArr = new char[PWD_LEN];
        for (int i = 0; i != PWD_LEN; i++) {
            int num = random.nextInt(CHAR_ARR_LEN);
            charArr[i] = CHAR_ARR[num];
        }

        // 初始化基本密码
        rootKey = String.valueOf(charArr);
    }

    /**
     * 生成随机密钥
     *
     * @return
     */
    public static String randKey() {

        Random random = new Random();
        // 将原串向后调 n 位，每隔几个重新生成字符
        // 随机生成调整位置
        int pos = random.nextInt(PWD_LEN);

        // 承机生成间隔步数
        int step = random.nextInt(7);
        // step = 0就会在求余的时候被0除
        if (step < 2) {
            step = 2;
        }

        char[] charArr = rootKey.toCharArray();
        char[] descCharArr = new char[PWD_LEN];

        for (int i = 0; i != PWD_LEN; i++) {
            if (i % step == 0) {
                int num = random.nextInt(CHAR_ARR_LEN);
                descCharArr[i] = CHAR_ARR[num];
            } else {
                descCharArr[i] = charArr[(pos + i) % PWD_LEN];
            }
        }

        String descPwd = new String(descCharArr);
        rootKey = descPwd;
        return descPwd;

    }

    /**
     * 加密
     *
     * @param src byte[]
     * @param key String
     * @return byte[]
     */
    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
        // 现在，获取数据并加密
        // 正式执行加密操作
        return cipher.doFinal(src);
    }

    /**
     * 加密
     *
     * @param src 明文
     * @param key 密钥
     * @return
     */
    public static String encryptToHex(String src, String key) throws Exception {

        byte[] descByte = encrypt(src.getBytes(), key.getBytes());
        return new String(Tools.bytesToHexString(descByte));

    }

    /**
     * 解密
     *
     * @param src byte[]
     * @param key String
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(key);
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }

    /**
     * 解密
     *
     * @param src 密文
     * @param key 密钥
     * @return
     */
    public static String decryptFromHex(String src, String key) throws Exception {
        byte[] descByte = decrypt(Tools.hexStringToBytes(src), key.getBytes());
        return new String(descByte);
    }
}
