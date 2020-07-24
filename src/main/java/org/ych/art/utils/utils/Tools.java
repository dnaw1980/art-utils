package org.ych.art.utils.utils;

import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用工具类
 */
public class Tools {

    /**
     * 随机数生成器
     */
    private static Random random = new Random(System.currentTimeMillis());

    final static private String STR_TRUE = "TRUE";

    final static private Boolean BOOL_TRUE = true;

    final static private String STR_FALSE = "FALSE";

    final static private Boolean BOOL_FALSE = false;

    /**
     * 根据串true \ false 返回 Boolean 类型 大小写不敏感
     *
     * @param strBoolean
     * @return
     */
    public static Boolean getBoolean(String strBoolean) {

        String boolStr = strBoolean.toUpperCase();

        if (STR_TRUE.equals(boolStr)) {
            return BOOL_TRUE;
        }

        if (STR_FALSE.equals(boolStr)) {
            return BOOL_FALSE;
        }

        return null;

    }

    /**
     * 根据串true \ false 返回 Boolean 类型 大小写不敏感
     *
     * @param objBoolean
     * @return
     */
    public static Boolean getBoolean(Object objBoolean) {

        Class<? extends Object> clazz = objBoolean.getClass();

        if (BigDecimal.class.equals(clazz)) {
            BigDecimal bd = (BigDecimal) objBoolean;
            if (bd.intValue() != 0) {
                return BOOL_TRUE;
            } else {
                return BOOL_FALSE;
            }
        }

        if (BigDecimal.class.equals(clazz)) {
            BigDecimal bd = (BigDecimal) objBoolean;
            if (bd.intValue() != 0) {
                return BOOL_TRUE;
            } else {
                return BOOL_FALSE;
            }
        }

        if (Short.class.equals(clazz)) {
            Short bd = (Short) objBoolean;
            if (bd.intValue() != 0) {
                return BOOL_TRUE;
            } else {
                return BOOL_FALSE;
            }
        }

        if (Integer.class.equals(clazz)) {
            Integer bd = (Integer) objBoolean;
            if (bd.intValue() != 0) {
                return BOOL_TRUE;
            } else {
                return BOOL_FALSE;
            }
        }

        if (Long.class.equals(clazz)) {
            Long bd = (Long) objBoolean;
            if (bd.intValue() != 0) {
                return BOOL_TRUE;
            } else {
                return BOOL_FALSE;
            }
        }

        return null;

    }

//    /**
//     * 判断字符串是否为数字，不能有小数点
//     *
//     * @param str
//     * @return
//     */
//    public static boolean isNumeric(String str) {
//        int len = str.length();
//        for (int i = 0; i != len; i++) {
//            if (!Character.isDigit(str.charAt(i))) {
//                return false;
//            }
//        }
//        return true;
//    }

    /**
     * 判断字符串是否为整型数字
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        int len = str.length();
        if (len < 1) {
            return false;
        }
        for (int i = 0; i != len; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否为数字,可以包含一个小数点
     *
     * @param str
     * @return
     */
    public static boolean isDigit(String str) {
        int len = str.length();
        if (len < 1) {
            return false;
        }
        int pointCount = 0;
        for (int i = 0; i != len; i++) {
            char c = str.charAt(i);
            if (c == '.') {
                pointCount++;
                if (pointCount > 1) {
                    return false;
                }
            } else if (!Character.isDigit(str.charAt(i))) {
                return false;
            }

        }
        if (pointCount == len) {
            return false;
        }
        return true;
    }

    // 替换建议内容中的特殊字符
    public static String htmlspecialchars(String str) {
        str = str.replace("&amp;", "&");
        str = str.replace("&lt;", "<");
        str = str.replace("&gt;", ">");
        str = str.replace("&quot;", "\"");
        // str = str.replace("\\n", "&nbsp;");
        // str = str.replace("\\t", "&nbsp;");
        return str;
    }

    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    private static final String regEx_space = "\\s*|\t|\r|\n";// 定义空格回车换行符

    /**
     * @param htmlStr
     * @return 删除Html标签
     */
    public static String delHTMLTag(String htmlStr) {
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
        return htmlStr.trim(); // 返回文本字符串
    }

    /**
     * 取HTML标签里的文本内容
     *
     * @param htmlStr
     * @return
     */
    public static String getTextFromHtml(String htmlStr) {
        htmlStr = htmlspecialchars(htmlStr);
        htmlStr = delHTMLTag(htmlStr);
        htmlStr = htmlStr.replaceAll("&nbsp;", "");
        // htmlStr = htmlStr.substring(0, htmlStr.indexOf("。")+1);
        return htmlStr;
    }

    /**
     * md5加密字符串
     */
    public final static String md5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 以|分隔的字符串转成，数字数组
     *
     * @param src
     * @return
     */
    public static List<BigDecimal> strToNumList(String src, String token) {
        List<BigDecimal> rs = null;
        if (src == null || src.equals("")) {
            return rs;
        }

        StringTokenizer st = new StringTokenizer(src, token);
        rs = new ArrayList<BigDecimal>(st.countTokens());
        while (st.hasMoreTokens()) {
            rs.add(new BigDecimal(st.nextToken()));
        }

        return rs;
    }

    private static Random RANDOM = new Random();

    /**
     * 在begin（包含） 到 end（不包含） 之间 产生 num 个随机数
     *
     * @param begin
     * @param end
     * @param num
     * @return
     */
    private static int[] getRandArray(int begin, int end, int num) {
        int[] rs = new int[num];

        int i = 0;
        while (i != num) {

            int randInt = RANDOM.nextInt(end);

            if (randInt < begin) {
                continue;
            }

            boolean isHas = false;
            for (int j = 0; j != i; j++) {
                if (randInt == rs[j]) {
                    isHas = true;
                    break;
                }
            }

            if (isHas) {
                continue;
            }
            rs[i] = randInt;
            i++;
        }

        return rs;
    }

    /**
     * 在给定集合中，随机选中 num 个元素
     *
     * @param baseColl
     * @param num
     * @return
     */
    public static Collection randSubSet(Collection baseColl, int num) {

        Collection rs = new HashSet();

        int size = baseColl.size();

        if (size <= num) {
            return baseColl;
        }

        int idxSet[] = getRandArray(0, size, num);
        int idxLen = idxSet.length;

        // 遍历集合元素
        Iterator it = baseColl.iterator();
        for (int i = 0; it.hasNext(); i++) {
            Object obj = it.next();

            // 如果当前集合的下标在 idxSet中存在，表示被选中
            for (int j = 0; j != idxLen; j++) {
                if (idxSet[j] == i) {
                    rs.add(obj);
                    break;
                }
            }
        }

        return rs;
    }

    /**
     * 返回一个随机生成的32位动态码
     *
     * @return
     */
    public static String getDyn32Code() {
        long currMillis = System.currentTimeMillis();
        currMillis <<= 32;
        currMillis |= RANDOM.nextInt();
        return md5(String.valueOf(currMillis));
    }

    /**
     * 返回一个UUID，全球唯一标识，带四个-分隔，36位长
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 返回一个全球唯一标识，没有-分隔，32位长
     *
     * @return
     */
    public static String getShortUUID() {
        return uuidRemoveToken(UUID.randomUUID().toString());
    }

    /**
     * 03000c9bdacc4fe3a8a5c039133887a8 <br>
     * 变成 <br>
     * 03000c9b-dacc-4fe3-a8a5-c039133887a8
     *
     * @return
     */
    public static String uuidAddToken(String uuid) {
		/*
		 03000c9bdacc4fe3a8a5c039133887a8
		 变成
		 03000c9b-dacc-4fe3-a8a5-c039133887a8
		 
		 */
        // 位置
        int[] UUID_TOKEN_POS = {8, 12, 16, 20};

        if (Tools.empty(uuid) || uuid.length() != 32) {
            throw new RuntimeException("输入的UUID原串长度不为 32");
        }

        char[] chars = uuid.toCharArray();
        char[] descChars = new char[36];
        int pos = 0;
        for (int i = 0; i < chars.length; i++) {
            if (pos != 4 && i == UUID_TOKEN_POS[pos]) {
                descChars[i + pos] = '-';
                pos++;
            }
            descChars[i + pos] = chars[i];
        }

        return String.valueOf(descChars);
    }

    /**
     * 03000c9b-dacc-4fe3-a8a5-c039133887a8 <br>
     * 变成<br>
     * 03000c9bdacc4fe3a8a5c039133887a8
     *
     * @return
     */
    public static String uuidRemoveToken(String uuid) {
		/*
		 
		 03000c9b-dacc-4fe3-a8a5-c039133887a8
		 变成
		 03000c9bdacc4fe3a8a5c039133887a8
		 
		 */
        // 位置
        int[] UUID_TOKEN_POS = {8, 12, 16, 20};

        if (Tools.empty(uuid) || uuid.length() != 36) {
            throw new RuntimeException("输入的UUID原串长度不为 36");
        }

        char[] chars = uuid.toCharArray();
        char[] descChars = new char[32];
        int pos = 0;
        for (int i = 0; i < descChars.length; i++) {

            if (pos != 4 && i == UUID_TOKEN_POS[pos]) {
                pos++;
            }

            descChars[i] = chars[i + pos];

        }

        return String.valueOf(descChars);
    }

    /**
     * 返回随机字节型
     *
     * @return
     */
    public static byte getRandomByte() {
        int i = random.nextInt();
        i &= 0x000000ff;
        return (byte) i;
    }

    /**
     * 返回随机短整型
     *
     * @return
     */
    public static short getRandomShort() {
        int i = random.nextInt();
        i &= 0x0000ffff;
        return (short) i;
    }

    /**
     * 返回随机整型
     *
     * @return
     */
    public static int getRandomInteger() {
        return random.nextInt();
    }

    /**
     * 返回随机长整型
     *
     * @return
     */
    public static long getRandomLong() {
        return random.nextLong();
    }

    /**
     * 集合 Collection 转换成数组
     *
     * @param collection
     * @param uCls 元素类型
     * @return
     */
    public static <U> U[] collectionToArray(Collection<U> collection, Class<U> uCls) {

        int arrLen = collection.size();

        @SuppressWarnings("unchecked")
        U uArray[] = (U[]) Array.newInstance(uCls, arrLen);

        Iterator<U> it = collection.iterator();
        for (int i = 0; i != arrLen; i++) {
            uArray[i] = it.next();
        }
        return uArray;

    }

    /**
     * 列表（List）转换成数组
     *
     * @param list
     * @param uCls
     * @return
     */
    public static <U> U[] listToArray(List<U> list, Class<U> uCls) {

        int arrLen = list.size();

        @SuppressWarnings("unchecked")
        U uArray[] = (U[]) Array.newInstance(uCls, arrLen);

        Iterator<U> it = list.iterator();
        for (int i = 0; i != arrLen; i++) {
            uArray[i] = it.next();
        }
        return uArray;

    }

    /**
     * 数组 T[], 转换成列表 （List）
     *
     * @param uArray
     * @return
     */
    public static <U> List<U> arrayTolist(U[] uArray) {

        int arrLen = uArray.length;

        List<U> uList = new ArrayList<U>(arrLen);

        for (int i = 0; i != arrLen; i++) {
            uList.add(uArray[i]);
        }
        return uList;

    }

    /**
     * 列表（List）转换成串
     *
     * @param list
     * @param token
     * @return
     */
    public static <U> String listToString(List<U> list, String token) {

        if (empty(list)) {
            return "";
        }

        StringBuilder strBuilder = new StringBuilder();

        Iterator<U> it = list.iterator();
        for (int i = 0; it.hasNext(); i++) {
            if (i != 0) {
                strBuilder.append(token);
            }
            strBuilder.append(it.next());
        }
        return strBuilder.toString();

    }

    // 以下都是判断空

    /**
     * 判断数组是否为空
     *
     * @param objects
     * @return
     */
    public static boolean empty(Object[] objects) {
        return objects == null || objects.length == 0;
    }

    /**
     * 判断数组是否
     * <h1>不为空</h1>
     *
     * @param objects
     * @return
     */
    public static boolean notEmpty(Object[] objects) {
        return objects != null && objects.length > 0;
    }

    // 以下都是判断空

    /**
     * 判断源字符串是否 string == null || "".equals(string)
     *
     * @param string
     * @return
     */
    public static boolean empty(String string) {
        return string == null || string.isEmpty();
    }

    /**
     * 判断源字符串是否
     * <h1>不为空</h1> string != null && !"".equals(string)
     *
     * @param string
     * @return
     */
    public static boolean notEmpty(String string) {
        return string != null && !string.isEmpty();
    }

    /**
     * 判断源集合是否 collection == null || collection.isEmpty()
     *
     * @param collection
     * @return
     */
    public static boolean empty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断源集合是否
     * <h1>不为空</h1> collection != null && !collection.isEmpty()
     *
     * @param collection
     * @return
     */
    public static boolean notEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    /**
     * 判断源MAP是否 map == null || map.isEmpty()
     *
     * @param map
     * @return
     */
    public static boolean empty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断源MAP是否
     * <h1>不为空</h1>map != null && !map.isEmpty()
     *
     * @param map
     * @return
     */
    public static boolean notEmpty(Map<?, ?> map) {
        return map != null && !map.isEmpty();
    }

    /**
     * 将 byte 字节数组转成 16进制字符串
     *
     * @param bytes
     * @return
     */
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (bytes == null || bytes.length <= 0) {
            return null;
        }

        for (byte b : bytes) {
            String hv = Integer.toHexString(b & 0xFF);
            if (b < 0x10 && b >= 0) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }

        return stringBuilder.toString();
    }

    /**
     * 将16进制字符串转成数组
     *
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (Tools.empty(hexString)) {
            return null;
        }

        int length = hexString.length();
        if (length % 2 == 1) {
            return null;
        }

        length /= 2;

        hexString = hexString.toUpperCase();

        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i != length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]) & 0xff);
        }
        return d;
    }

    /**
     * @param c
     * @return
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 将对象生成字节数组
     *
     * @param t
     * @return
     * @throws IOException
     */
    public static <T extends Serializable> byte[] objectToBytes(T t) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        byte[] bytes = null;

        ObjectOutputStream so = new ObjectOutputStream(bo);
        so.writeObject(t);

        bytes = bo.toByteArray();

        so.flush();
        so.close();
        bo.close();

        return bytes;
    }

    /**
     * 将对象生成16进制字符串
     *
     * @param t
     * @return
     * @throws IOException
     */
    public static <T extends Serializable> String objectToHexString(T t) throws IOException {
        byte[] bytes = objectToBytes(t);
        if (bytes != null) {
            return bytesToHexString(bytes);
        } else {
            return null;
        }

    }

    /**
     * 将字节数组反串行化成对象
     *
     * @param bytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {

        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        T t = null;

        ObjectInputStream si = new ObjectInputStream(bi);
        t = (T) si.readObject();
        si.close();
        bi.close();

        return t;
    }

    /**
     * 将16进制字符串反串行化成对象
     *
     * @param hexString
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T extends Serializable> T hexStringToObject(String hexString)
            throws ClassNotFoundException, IOException {
        byte[] bytes = Tools.hexStringToBytes(hexString);
        if (bytes != null) {
            return bytesToObject(bytes);
        } else {
            return null;
        }
    }

    /**
     * 拼接字符串
     *
     * @param strings
     * @return
     */
    public static String builderString(String... strings) {

        if (strings == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder("");
        for (String string : strings) {
            sb.append(string);
        }

        return sb.toString();

    }

    /**
     * 获取double小数位,取一位小数
     *
     * @param number
     * @return
     */
    public static double getDoubleDecimal(double number) {
        int number_int = (int) number;
        DecimalFormat df = new DecimalFormat("0.0");
        double returnvalue = new Double(df.format(number - number_int));
        return returnvalue;
    }

    /**
     * 获取double进位数
     *
     * @param number
     * @return
     */
    public static int getDoubleCarry(double number) {
        if (getDoubleDecimal(number) > 0) {
            int number_int = (int) number + 1;
            return number_int;
        } else {
            int number_int = (int) number;
            return number_int;
        }
    }

    /**
     * 保留两位小数
     */

    public static BigDecimal formatBigDecimal(double formatData) {

        DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(2);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.FLOOR);

        return new BigDecimal(formater.format(formatData));

    }

    /**
     * 保留两位小数
     */

    public static BigDecimal formatBigDecimal(BigDecimal formatData) {

        DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(2);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.FLOOR);

        return new BigDecimal(formater.format(formatData));

    }

    /**
     * 身份证号计算生日
     *
     * @param idCard
     * @return
     * @throws ParseException
     */
    public static java.sql.Date idcardToBirth(String idCard) throws ParseException {

        if (idCard == null || idCard.length() != 18) {
            return null;
        }

        Date dt = new SimpleDateFormat("yyyyMMdd").parse(idCard.substring(6, 14));

        return new java.sql.Date(dt.getTime());

    }

    /**
     * TODO 根据身份编号获取性别，有误，需要更正
     *
     * @param idCard 身份编号
     * @return 性别(1 - 男 ， 0 - 女)
     */
    public static byte getGenderByIdCard(String idCard) {

        String sCardNum = idCard.substring(16, 17);
        if (Integer.parseInt(sCardNum) % 2 != 0) {
            return 1;
        } else {
            return 0;
        }
    }

}
