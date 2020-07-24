package org.ych.art.utils.rsa;

import static org.junit.Assert.assertTrue;

import java.util.Base64;

import org.junit.BeforeClass;
import org.junit.Test;

public class RsaTest {

    /**
     * 公钥串
     */
    private static String publicKeyString = "";

    /**
     * 私钥串
     */
    private static String privateKeyString = "";

    private static final int REPT_COUNT = 0;

    private static int testNum = 0;

    private static String strTolong(String src, int cate) {
        StringBuilder sb = new StringBuilder(src);
        for (int i = 0; i < cate; i++) {
            sb.append(src);
        }

        return sb.toString();
    }

    @BeforeClass
    public static void initKey() {
        // KeyStringPair keyStringPair = RSAEncrypt.genKeyStringPair();

        System.out.println("--------------密钥-------------------");

        System.out.println("--------------公钥-------------------");
        // publicKeyString = keyStringPair.getPublicKey();
        publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzkAbbtg7BMlFJQzG+N9NXS/IOuHhDcZJ7avB3b+It5JXURmbG9W/pesdismBVTT1cmo/5+CKddhzVqXIF/EAXrKvrw6iL9v9fljwfKjEvdqZje8UEEVMUf+J1d5V+P7K7zLUw9nVakwqIFt6a/FYYOsc+xBXoR7mtxPSmTCfmqQIDAQAB";
        System.out.println(publicKeyString);

        System.out.println("--------------私钥-------------------");
        // privateKeyString = keyStringPair.getPrivateKey();
        privateKeyString = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALOQBtu2DsEyUUlDMb4301dL8g64eENxkntq8Hdv4i3kldRGZsb1b+l6x2KyYFVNPVyaj/n4Ip12HNWpcgX8QBesq+vDqIv2/1+WPB8qMS92pmN7xQQRUxR/4nV3lX4/srvMtTD2dVqTCogW3pr8Vhg6xz7EFehHua3E9KZMJ+apAgMBAAECgYAIRYs7VdCqL7I06MaC17mBnPsCmJbZrIKt8sgvCFVn/c61H6UeZzIHlGNY9ZSgeVVpTalyP28Ax+8eHJcnFW6yVLyBO2GMBpFmu1efk3mlvN0lvezuIW0zvureqO7Z+G5aNq8tXsSaQHAVoj8B1G6CA7yj9PK57POUr+AR0YnTbQJBANvirUqKOAwWuwQcUVJ1Xm6E2/N6bU9E5OyUt7frtExDexs0RRmYDeHEn8vxtzfscLZNVXkew+jlRvQhXq8sJfMCQQDRDe6FF+8AElZTWEkLGewfi2N83yWxvw5o8Cl06hKY7ffvXyr0scwjS6rfQ7b1JFaEKpFk2uplp76gQfcBU9vzAkEAgSp9JYZgOim4HkgMNWYg3MdK9ZH7WE9eMuOBsJbTSOFzFZ304X4C2ZbJT+JkAIPyCgUPqqC58LuOnKZlmn1SpQJBAJ6XOAwFpQzj3FMr1XWze5pOjvGrAxh9fPwEvR5xeBxF4uHXy2mH+/oGrCDlaMU9hNiMxnHBVTI69/TxNeXu7RsCQAuxZulR36AAaVDVNqYEmj01+pqO7FBWZDu/UAmDXMLOAiFzxN+nFjkLjmlnD6c3f7nB7e7MLdX8Z/I0FS0fPjk=";
        System.out.println(privateKeyString);

    }

    /**
     * 公钥加密私钥解密
     *
     * @throws Exception
     */
    @Test
    public void publicToPrivateString() throws RSAException {
        System.out.printf("\n第 %d 号测试\t公钥加密私钥解密过程-使用串------------------\n", ++testNum);

        String plainText = strTolong("公钥加密密adAIE&*(=,!<>‘“'\"", REPT_COUNT);
        System.out.println(plainText.length());

        System.out.println("原文：" + plainText);
        // 公钥加密过程

        String cipher = RSAEncrypt.encryptPublic(publicKeyString, plainText);
        System.out.println("加密：" + cipher);

        // 私钥解密过程
        String restr = RSAEncrypt.decryptPrivate(privateKeyString, cipher);

        System.out.println("解密：" + restr);

        boolean rs = plainText.equals(restr);
        System.out.println("原文密文是否相同？\t" + rs);
        assertTrue(rs);
    }

    /**
     * 公钥加密私钥解密
     *
     * @throws Exception
     */
    @Test
    public void publicToPrivate() throws RSAException {
        System.out.printf("\n第 %d 号测试\t公钥加密私钥解密过程-------------------\n", ++testNum);

        String plainText = strTolong("公钥加密密adAIE&*(=,!<>‘“'\"", REPT_COUNT);
        System.out.println(plainText.length());

        byte[] plainBytes = plainText.getBytes();
        System.out.println(plainBytes.length);
        System.out.println("原文：" + plainText);
        // 公钥加密过程
        byte[] cipherData = RSAEncrypt.encryptPublic(publicKeyString, plainBytes);

        // String cipher = Base64.encode(cipherData);
        String cipher = Base64.getEncoder().encodeToString(cipherData);
        System.out.println("加密：" + cipher);
        // 私钥解密过程
        // byte[] res = RSAEncrypt.decryptPrivate(privateKeyString,
        // Base64.decode(cipher));
        byte[] res = RSAEncrypt.decryptPrivate(privateKeyString, Base64.getDecoder().decode(cipher));
        String restr = new String(res);

        System.out.println("解密：" + restr);

        boolean rs = plainText.equals(restr);
        System.out.println("原文密文是否相同？\t" + rs);
        assertTrue(rs);
    }

    /**
     * 公钥加密公钥解密
     *
     * @throws RSAException
     * @throws Exception
     */
    @Test
    public void publicToPublicString() throws RSAException {
        System.out.printf("\n第 %d 号测试\t公钥加密公钥解密过程-使用串------------------\n", ++testNum);

        String plainText = strTolong("公钥加密密adAIE&*(=,!<>‘“'\"", REPT_COUNT);
        System.out.println(plainText.length());
        System.out.println("原文：" + plainText);

        // 公钥加密过程
        String cipher = RSAEncrypt.encryptPublic(publicKeyString, plainText);
        System.out.println("加密：" + cipher);
        // 公钥解密过程
        try {
            String restr = RSAEncrypt.decryptPublic(publicKeyString, cipher);

            System.out.println("解密：" + restr);

            boolean rs = plainText.equals(restr);
            System.out.println("原文密文是否相同？\t" + rs);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue(e instanceof RSAException);

        }

    }

    /**
     * 公钥加密公钥解密
     *
     * @throws RSAException
     * @throws Exception
     */
    @Test
    public void publicToPublic() throws RSAException {
        System.out.printf("\n第 %d 号测试\t公钥加密公钥解密过程-------------------\n", ++testNum);

        String plainText = strTolong("公钥加密密adAIE&*(=,!<>‘“'\"", REPT_COUNT);
        System.out.println(plainText.length());

        byte[] plainBytes = plainText.getBytes();
        System.out.println(plainBytes.length);
        System.out.println("原文：" + plainText);
        // 公钥加密过程
        byte[] cipherData = null;
        cipherData = RSAEncrypt.encryptPublic(publicKeyString, plainBytes);

        // String cipher = Base64.encode(cipherData);
        String cipher = Base64.getEncoder().encodeToString(cipherData);
        System.out.println("加密：" + cipher);
        // 公钥解密过程
        byte[] res = null;
        try {
            // res = RSAEncrypt.decryptPublic(publicKeyString, Base64.decode(cipher));
            res = RSAEncrypt.decryptPublic(publicKeyString, Base64.getDecoder().decode(cipher));

            String restr = new String(res);
            System.out.println("解密：" + restr);

            boolean rs = plainText.equals(restr);
            System.out.println("原文密文是否相同？\t" + rs);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue(e instanceof RSAException);

        }

    }

    /**
     * 私钥加密公钥解密
     *
     * @throws RSAException
     * @throws Exception
     */
    @Test
    public void privateToPublicString() throws RSAException {
        System.out.printf("\n第 %d 号测试\t私钥加密公钥解密过程-使用串------------------\n", ++testNum);
        String plainText = strTolong("ihep_私钥加密公钥解密adAIE&*(=,!<>‘“'\"", REPT_COUNT);
        System.out.println("原文：" + plainText);
        // 私钥加密过程
        String cipher = RSAEncrypt.encryptPrivate(privateKeyString, plainText);

        System.out.println("加密：" + cipher);
        // 公钥解密过程
        String restr = RSAEncrypt.decryptPublic(publicKeyString, cipher);

        System.out.println("解密：" + restr);
        boolean rs = plainText.equals(restr);
        System.out.println("原文密文是否相同？\t" + rs);
        assertTrue(rs);
    }

    /**
     * 私钥加密公钥解密
     *
     * @throws RSAException
     * @throws Exception
     */
    @Test
    public void privateToPublic() throws RSAException {
        System.out.printf("\n第 %d 号测试\t私钥加密公钥解密过程-------------------\n", ++testNum);
        String plainText = strTolong("ihep_私钥加密公钥解密adAIE&*(=,!<>‘“'\"", REPT_COUNT);
        System.out.println("原文：" + plainText);
        // 私钥加密过程
        byte[] cipherData = null;
        cipherData = RSAEncrypt.encryptPrivate(privateKeyString, plainText.getBytes());

        // String cipher = Base64.encode(cipherData);
        String cipher = Base64.getEncoder().encodeToString(cipherData);
        System.out.println("加密：" + cipher);
        // 公钥解密过程
        byte[] res = null;
        // res = RSAEncrypt.decryptPublic(publicKeyString, Base64.decode(cipher));
        res = RSAEncrypt.decryptPublic(publicKeyString, Base64.getDecoder().decode(cipher));

        String restr = new String(res);

        System.out.println("解密：" + restr);
        boolean rs = plainText.equals(restr);
        System.out.println("原文密文是否相同？\t" + rs);
        assertTrue(rs);
    }

    /**
     * 私钥加密私钥解密
     *
     * @throws RSAException
     * @throws Exception
     */
    @Test
    public void privateToPrivateString() throws RSAException {
        System.out.printf("\n第 %d 号测试\t私钥加密私钥解密过程-使用串------------------\n", ++testNum);
        String plainText = strTolong("ihep_私钥加密私钥解密adAIE&*(=,!<>‘“'\"", REPT_COUNT);
        System.out.println("原文：" + plainText);
        // 私钥加密过程
        String cipher = RSAEncrypt.encryptPrivate(privateKeyString, plainText);
        System.out.println("加密：" + cipher);
        // 公钥解密过程
        try {
            String restr = RSAEncrypt.decryptPrivate(privateKeyString, cipher);

            System.out.println("解密：" + restr);
            boolean rs = plainText.equals(restr);
            System.out.println("原文密文是否相同？\t" + rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue(e instanceof RSAException);

        }

    }

    /**
     * 私钥加密私钥解密
     *
     * @throws RSAException
     * @throws Exception
     */
    @Test
    public void privateToPrivate() throws RSAException {
        System.out.printf("\n第 %d 号测试\t私钥加密私钥解密过程-------------------\n", ++testNum);
        String plainText = strTolong("ihep_私钥加密私钥解密adAIE&*(=,!<>‘“'\"", REPT_COUNT);
        System.out.println("原文：" + plainText);
        // 私钥加密过程
        byte[] cipherData = null;
        cipherData = RSAEncrypt.encryptPrivate(privateKeyString, plainText.getBytes());
        // String cipher = Base64.encode(cipherData);
        String cipher = Base64.getEncoder().encodeToString(cipherData);
        System.out.println("加密：" + cipher);
        // 公钥解密过程
        byte[] res = null;
        try {
            // res = RSAEncrypt.decryptPrivate(privateKeyString, Base64.decode(cipher));
            res = RSAEncrypt.decryptPrivate(privateKeyString, Base64.getDecoder().decode(cipher));

            String restr = new String(res);

            System.out.println("解密：" + restr);
            boolean rs = plainText.equals(restr);
            System.out.println("原文密文是否相同？\t" + rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue(e instanceof RSAException);

        }

    }

    /**
     * 私钥签名，公钥验证
     */
    @Test
    public void signToCheck() {
        System.out.printf("\n第 %d 号测试\t私钥签名过程-------------------\n", ++testNum);

        String content = strTolong("ihep_私钥加密公钥解密adAIE&*(=,!<>‘“'\"", REPT_COUNT);
        System.out.println("签名原串：" + content);

        String signstr = null;
        try {
            signstr = RSASignature.sign(content, privateKeyString);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("签名串：" + signstr);

        System.out.println("---------------公钥校验签名------------------");
        boolean rs = RSASignature.doCheck(content, signstr, publicKeyString);

        System.out.println("验签结果：" + rs);
        assertTrue(rs);
    }

    /**
     * 独立测试私钥解密
     *
     * @throws Exception
     */
    @Test
    public void singleTestPrivateDe() throws RSAException {
        System.out.printf("\n第 %d 号测试\t独立测试私钥解密过程-------------------\n", ++testNum);
        // String cipher =
        // "Jaj/tX2REMe/jEiE+KL7kauA7S4mX+hyjaj4TgCFW4uryylMP1m7Yetg2ntSsx00HPTuBN9SGfsarAmRlG6ZW3yucqYQy28aXcSscbYtQ2Crv4p7BlDY+vnssFhL18h8NIAWUypypJ81B3lCaRmRw6cUCQWbA9Sz5oz3VOrM+9o=";//
        // Base64.encode(cipherData);

        // String cipher =
        // "HHsxWyoYJm6t4hK/aXFQNiNfCK8XiXHiKbWGZ6kaPQylc92JcuC+ZLHZvjwGQ+S2sxPWt8r6Qvr4SlG9qvmKDXRBlca0HVkIvYg1A3TQaIjcJbidXAYG0fReCuneIBl3xH+1f1oJIZPWhcleWXYmftH0xPEZdxOmg3GGBX7HyzM=";
        // String cipher =
        // "Ka8Qdbi/yIJpx+O6wn7YpHktsCtOftpuEq86A5XmNYXUEFyXgxKt4NXS1VxbjCNvBzeIg2n/ssP4A1YjTO9mWAoJgzoQqJ1TWt1SYoDRIEu5WGQI7V4x4fAF3UUk0DOE5rhIv9tr/T0bo0ed5bTSP0aVfwCY96Mhy85RA8/e1nk=";

        String cipher = "ksv7FM60saeRfiJ7cmnkylPjOOwI7nWzoOn6i7e08lu31potZmaBNgAiaiPJKYZbCLWwl+6rorKn0lOuS0XCt6Qsm2qOlfo1tqZo++D5+SPnF3F/aaO9NjzrppsPK35UjVQXy9mFzNH/nWJzs30h8sjCBdPgBW0uMbTrvYEpT5g=";
        System.out.println("加密：" + cipher);
        // 私钥解密过程
        byte[] res = RSAEncrypt.decryptPrivate(privateKeyString, Base64.getDecoder().decode(cipher));
        String restr = new String(res);

        System.out.println("解密：" + restr);

        assertTrue(true);
    }

    /**
     * 独立测试公钥解密
     *
     * @throws Exception
     */
    @Test
    public void singleTestPublicDe() throws RSAException {
        System.out.printf("\n第 %d 号测试\t独立测试公钥解密过程-------------------\n", ++testNum);

        String cipher = "qbjt7/L3zrHSwO4lT8wYdebDm5zNcGcMar2wXfQm3Dhc5t4IsNt18jHMpXLTIb7fZa08kAt+YtqMgm6Px4gXRFrHLrqIKWWYGKSZQXKzgw49m2vrJ8oak0DIZoT2z02RTQaJqT4eHEZwZcPOKFGkIVkf+hYhD3UxFY3FVMx8aZQ=";// Base64.encode(cipherData);

        System.out.println("加密：" + cipher);
        // 私钥解密过程
        // byte[] res = RSAEncrypt.decryptPublic(publicKeyString,
        // Base64.decode(cipher));
        byte[] res = RSAEncrypt.decryptPublic(publicKeyString, Base64.getDecoder().decode(cipher));
        String restr = new String(res);

        System.out.println("解密：" + restr);

        assertTrue(true);
    }
}
