package org.ych.art.utils.utils;

import java.util.Random;

/**
 * 生成六位数字验证码，给手机用
 *
 */
public class ChkCode implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Random rd = new Random(System.currentTimeMillis());

	private long _genMillSecs;

	private String _code;

	public ChkCode(String code) {
		this._genMillSecs = System.currentTimeMillis();
		this._code = code;
	}

	/**
	 * @return 返回生成验证码的时间
	 */
	public long getGenMillSecs() {
		return _genMillSecs;
	}

	/**
	 * @return 返回验证码
	 */
	public String getCode() {
		return _code;
	}

	public static ChkCode genCode() {
		final int chLen = 6;
		char chs[] = new char[chLen];

		for (int i = 0; i != chLen; i++) {
			chs[i] = (char) (rd.nextInt(9) + 48);
		}

		String code = String.valueOf(chs);

		ChkCode cc = new ChkCode(code);

		return cc;

	}
}
