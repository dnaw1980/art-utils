package org.ych.art.utils.shanba;

import org.ych.art.utils.des.DESHelper;
import org.ych.art.utils.rsa.KeyStringPair;
import org.ych.art.utils.rsa.RSAHelper;
import org.ych.art.utils.utils.Tools;

public class TestRSACert {
    // public static String pfxPath = "D:\\陕坝测试\\rsa_private_key_2048.pem";//私钥地址
    // public static String certPath = "D:\\陕坝测试\\rsa_public_key_2048.pem";//公钥地址
    // private static String certPasswd = "password";// 证书密码

    // public static String priKeyStr =
    // "MIIEowIBAAKCAQEAuyb5AEZvY/4BhmXrpUGzMSLHFQ1G5BcnYZbaWDbUWabDeoKU"
    // + "u88BJ/sIxr9VqBZt0KOr10h0b7qHXpIuuXB5VUqgnDTzuo+O6PWJElWozL03/F8S"
    // + "7TAd4nihugu4cggIHJItZOSaN1hvO5RM5hpwfOLnmBaDzR4aqe1zSWTnBoZ5Ox0p"
    // + "r3EmdG9Q+yN1dGazqqMYRLnr8H4nG1aSkPbTgDYNWF5YehDDqaOMTQShYhAD726i"
    // + "t9XHh0AnHt5ZA4JLu2ooar9hU7RaNT0T4+avusEgSOotVeikowg1EC6HHCgInuET"
    // + "mBrrWJryfVK1vCk1HnbjztvZCQcinQxk7/gOCwIDAQABAoIBAELTWqvfepzAsPi1"
    // + "YdwDRqGv4lhBAmQlZFlzTQmcolxUsI4JHGSnOKl2+J5G6aX4REPwfUaEx5axiu6Y"
    // + "0fhDEgADgq/kNHcUFZpXOheC4KAinGAP7qkkujcIje7pinWlKohRJcZV3j8O48Dp"
    // + "Zj/vhuUA/GMQ/wT5z9mHQNgfIGLyeggW/UCZ3puFGowVf1aAre5e0awCQ6Uatvdw"
    // + "uNhyJ6stDOx5OrUp6XMx2y+OPT7VmK3lsGx+nWoRFjIcnr8Xc42zplbHYMFX+m04"
    // + "PSv7/PESIC/8QtRXYQZi0SmW/sxhTxxR5M90vob4lsD6UKrTnBgzabcNROxuFDMY"
    // + "qkQpjfECgYEA9HOQ7FNq1DR/Voq/d59p8z+nsykVC9Vr14PL8ia1VdZ5xtNOJJjX"
    // + "XWcKfMpLa7iUUqyLMwfVLt8gruIbDaERz/uai8+9+DRy5cj2CxNc2oiIwJfDBuNO"
    // + "k7+R9P9NPwWfgmwk6/j9uvND6NmIhTFLF85G6jGTOjFU1UXQ6hAdy7kCgYEAw/5r"
    // + "z4eCONq8xM9/+H5P1Xy6nAyPE40XlUPmYr8BdaP+xBGrbJNydEifUF4dVPtKwOZQ"
    // + "B/zw2uZuFFsOknaC9uVU+isHoSW5VaW8iJOzcr/He/Ky38VFWEtm2iLikXeqbpKO"
    // + "SH3UUeKp+XyR+XAHjhIhwR3efqOtnO+lUVD+MeMCgYBUkOZB21nJr5+dwMIcD/oH"
    // + "PK43PK8bOOnl1KJpUxrIZzoZnmAaKQXYOh1WrmUJDwHA2BAPx1XosSgNWAsj2+Td"
    // + "np0IYQXHJ93XwH3dtwUYVMN6XQYLC/fTBSLpLqj6dGeKJv9e4vRkCGQl2Ztw+GyR"
    // + "/7poql3Uc9x1K4Hqd0jXgQKBgEWlzrgDxpHfoT8rIaWzdTk/Rq0XhWyEMgI4C9HB"
    // + "YIpmia0xPkKqLv0FpZ+QoknFtu3Pqlb8WrbfNfUD2qzPcoC8RDNWQTLRzgeSiunZ"
    // + "2uYHIqN4Pnpohtj2iokv5mWdKbJ7YBaAqIekzZQ74T/LwNOKu/X9YEiOb66aNpwr"
    // + "9PjTAoGBAKEOhN3yr2V+v2Xi9RMfXWevBgPjf4Q6lYmWDMZnirwMIoYpOO2Xfmmi"
    // + "L1XA4PnNxHPNef3mJDxPU7D7GyF+QKcDC0vXns/YwIDbaYSdGzPt8WdC+3BYh290"
    // + "FxvcFiV/zTy4pAGvXpPwgapkeLKsgCJPM2rhgB0zUVY5iL8bAuZu";// 私钥地址

    public static String priKeyStr = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC7JvkARm9j/gGG"
            + "ZeulQbMxIscVDUbkFydhltpYNtRZpsN6gpS7zwEn+wjGv1WoFm3Qo6vXSHRvuode"
            + "ki65cHlVSqCcNPO6j47o9YkSVajMvTf8XxLtMB3ieKG6C7hyCAgcki1k5Jo3WG87"
            + "lEzmGnB84ueYFoPNHhqp7XNJZOcGhnk7HSmvcSZ0b1D7I3V0ZrOqoxhEuevwficb"
            + "VpKQ9tOANg1YXlh6EMOpo4xNBKFiEAPvbqK31ceHQCce3lkDgku7aihqv2FTtFo1"
            + "PRPj5q+6wSBI6i1V6KSjCDUQLoccKAie4ROYGutYmvJ9UrW8KTUeduPO29kJByKd"
            + "DGTv+A4LAgMBAAECggEAQtNaq996nMCw+LVh3ANGoa/iWEECZCVkWXNNCZyiXFSw"
            + "jgkcZKc4qXb4nkbppfhEQ/B9RoTHlrGK7pjR+EMSAAOCr+Q0dxQVmlc6F4LgoCKc"
            + "YA/uqSS6NwiN7umKdaUqiFElxlXePw7jwOlmP++G5QD8YxD/BPnP2YdA2B8gYvJ6"
            + "CBb9QJnem4UajBV/VoCt7l7RrAJDpRq293C42HInqy0M7Hk6tSnpczHbL449PtWY"
            + "reWwbH6dahEWMhyevxdzjbOmVsdgwVf6bTg9K/v88RIgL/xC1FdhBmLRKZb+zGFP"
            + "HFHkz3S+hviWwPpQqtOcGDNptw1E7G4UMxiqRCmN8QKBgQD0c5DsU2rUNH9Wir93"
            + "n2nzP6ezKRUL1WvXg8vyJrVV1nnG004kmNddZwp8yktruJRSrIszB9Uu3yCu4hsN"
            + "oRHP+5qLz734NHLlyPYLE1zaiIjAl8MG406Tv5H0/00/BZ+CbCTr+P2680Po2YiF"
            + "MUsXzkbqMZM6MVTVRdDqEB3LuQKBgQDD/mvPh4I42rzEz3/4fk/VfLqcDI8TjReV"
            + "Q+ZivwF1o/7EEatsk3J0SJ9QXh1U+0rA5lAH/PDa5m4UWw6SdoL25VT6KwehJblV"
            + "pbyIk7Nyv8d78rLfxUVYS2baIuKRd6puko5IfdRR4qn5fJH5cAeOEiHBHd5+o62c"
            + "76VRUP4x4wKBgFSQ5kHbWcmvn53AwhwP+gc8rjc8rxs46eXUomlTGshnOhmeYBop"
            + "Bdg6HVauZQkPAcDYEA/HVeixKA1YCyPb5N2enQhhBccn3dfAfd23BRhUw3pdBgsL"
            + "99MFIukuqPp0Z4om/17i9GQIZCXZm3D4bJH/umiqXdRz3HUrgep3SNeBAoGARaXO"
            + "uAPGkd+hPyshpbN1OT9GrReFbIQyAjgL0cFgimaJrTE+Qqou/QWln5CiScW27c+q"
            + "Vvxatt819QParM9ygLxEM1ZBMtHOB5KK6dna5gcio3g+emiG2PaKiS/mZZ0psntg"
            + "FoCoh6TNlDvhP8vA04q79f1gSI5vrpo2nCv0+NMCgYEAoQ6E3fKvZX6/ZeL1Ex9d"
            + "Z68GA+N/hDqViZYMxmeKvAwihik47Zd+aaIvVcDg+c3Ec815/eYkPE9TsPsbIX5A"
            + "pwMLS9eez9jAgNtphJ0bM+3xZ0L7cFiHb3QXG9wWJX/NPLikAa9ek/CBqmR4sqyA" + "Ik8zauGAHTNRVjmIvxsC5m4=";
    public static String pubKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuyb5AEZvY/4BhmXrpUGz"
            + "MSLHFQ1G5BcnYZbaWDbUWabDeoKUu88BJ/sIxr9VqBZt0KOr10h0b7qHXpIuuXB5"
            + "VUqgnDTzuo+O6PWJElWozL03/F8S7TAd4nihugu4cggIHJItZOSaN1hvO5RM5hpw"
            + "fOLnmBaDzR4aqe1zSWTnBoZ5Ox0pr3EmdG9Q+yN1dGazqqMYRLnr8H4nG1aSkPbT"
            + "gDYNWF5YehDDqaOMTQShYhAD726it9XHh0AnHt5ZA4JLu2ooar9hU7RaNT0T4+av"
            + "usEgSOotVeikowg1EC6HHCgInuETmBrrWJryfVK1vCk1HnbjztvZCQcinQxk7/gO" + "CwIDAQAB";// 公钥地址

    private static String hexXmlEnc;
    private static String hexKeyEnc;
    private static String hexSign;
    static RSAHelper rsaHelper = new RSAHelper();

    public void init() throws Exception {
        // PrivateKey prikey = rsaHelper.getPrivateKeyFromPfx(pfxPath, certPasswd);//
        // 获取证书私钥
        // PublicKey pk = rsaHelper.getPublicKeyFromCer(certPath);
        // rsaHelper.initKey(prikey, pk);

        KeyStringPair keyPair = new KeyStringPair();
        keyPair.setPrivateKey(priKeyStr);
        keyPair.setPublicKey(pubKeyStr);

        // keyPair.getKeyPair();

        rsaHelper.initKey(keyPair.loadPrivateKey(), keyPair.loadPublicKey());
    }

    public static void main(String[] args) throws Exception {
        TestRSACert testRSACert = new TestRSACert();
        testRSACert.init();
        encryptAndSign();
        decryptAndVeriSign();
    }

    private static void encryptAndSign() {
        String data = "Hello World!测试";
        // String key =
        // "1234562345678923456789234567892345678923456789234567892345678923456789234567892345678923456789234567892345678923456789789";
        String key = DESHelper.randKey();
        System.out.println("明文:" + data);
        System.out.println("密钥:" + key);
        try {

            // hexXmlEnc = DESHelper.encryptToHex(, key).desEncryptToHex(data,
            // key.getBytes());
            hexXmlEnc = DESHelper.encryptToHex(data, key);
            System.out.println("明文加密结果:" + hexXmlEnc);

            byte[] bkey = rsaHelper.encryptRSA(key.getBytes(), false, "utf-8");
            // hexKeyEnc = DESHelper.hexEncode(bkey);
            hexKeyEnc = Tools.bytesToHexString(bkey);
            System.out.println("密钥加密结果:" + hexKeyEnc);

            byte[] sign = rsaHelper.signRSA(data.getBytes("utf-8"), false, "utf-8");
            // hexSign = DESHelper.hexEncode(sign);
            hexSign = Tools.bytesToHexString(sign);
            System.out.println("签名结果:" + hexSign);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void decryptAndVeriSign() {
        try {
            // byte[] bkey = rsaHelper.decryptRSA(DESHelper.hexDecode(hexKeyEnc), false,
            // "utf-8");
            byte[] bkey = rsaHelper.decryptRSA(Tools.hexStringToBytes(hexKeyEnc), false, "utf-8");

            String keyStr = new String(bkey);
            System.out.println("密钥解密结果:" + keyStr);

            // String xml = DESHelper.desDecryptFromHex(hexXmlEnc, bkey);
            String xml = DESHelper.decryptFromHex(hexXmlEnc, keyStr);
            System.out.println("明文解密结果:" + xml);

            // byte[] bsign = DESHelper.hexDecode(hexSign);
            byte[] bsign = Tools.hexStringToBytes(hexSign);
            boolean ret = rsaHelper.verifyRSA(xml.getBytes("utf-8"), bsign, false, "utf-8");
            System.out.println("验签结果:" + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}