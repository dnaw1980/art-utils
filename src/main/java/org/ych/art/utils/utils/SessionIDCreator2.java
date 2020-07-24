package org.ych.art.utils.utils;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Session 随机 唯一ID生成器
 * 
 * 采用:随机数+随机字符 + 系统时间毫秒数 + 顺序码
 * 
 * @author 姚赪海
 *
 */
public class SessionIDCreator2 {

	/**
	 * 
	 */

	/**
	 * 随机数生成器
	 */
	private static Random random = new Random(System.currentTimeMillis());
	
	/**
	 * 随机数生成器
	 */
	private static int OFFER_SET = 0;

	/**
	 * 顺序数的起始数，也使用随机
	 */
	private static AtomicLong serialNumber = new AtomicLong(0x7FFFFFFF & random.nextInt());

	static {
			OFFER_SET = random.nextInt();
	}

	/**
	 * 生成 唯一ID字串
	 * 
	 * @return 唯一ID串
	 */
	public static String createUUSessionID() {

		StringBuilder idStr = new StringBuilder();

		// 返回系统时间
		long t1 = 0x7FFFFFFF & System.currentTimeMillis();

		/*
		 * 使用随机数为下标取随机码，因为随机码初始排列也是随机的，一起运行的多台机器初始化相同的机会非常小 所以，如果随机数相同，随机码必不相同。
		 */

		String hex = Long.toHexString(t1);
		int len = hex.length();

		while (len < 8) {
			idStr.append("0");
			len++;
		}
		idStr.append(hex).append("-");

		// 返回随机数
		int randomNumber = 0x7FFFFFFF & random.nextInt();


		hex = Integer.toHexString(randomNumber + OFFER_SET);
		len = hex.length();

		while (len < 8) {
			idStr.append("0");
			len++;
		}
		idStr.append(hex).append("-");

		hex = Integer.toHexString(randomNumber);
		len = hex.length();

		while (len < 8) {
			idStr.append("0");
			len++;
		}
		idStr.append(hex).append("-");
		/*
		 * 接一个顺序数，顺序数以16进制表示，顺序数的起始值也是唯一的。 如果是同一台机器，同一时刻产生同一随机数，顺序码必不相同。
		 */

		hex = Long.toHexString(serialNumber.addAndGet(1L));
		len = hex.length();

		while (len < 8) {
			idStr.append("0");
			len++;
		}
		idStr.append(hex);

		return idStr.toString();
	}

}
