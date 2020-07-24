package org.ych.art.utils.des;

public class DESHelperTest {

	public static void main(String[] args) {
		// for (int i = 0; i < 10; i++) {
		// System.out.println(DESHelper.randPwd());
		// }

		String key = DESHelper.randKey();

		System.out.println("key:\n" + key);

		String src = "这里是原文";
		System.out.println("src：\n" + src);

		// 加密
		String desc = null;
		try {
			desc = DESHelper.encryptToHex(src, key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("desc：\n" + desc);

		//解密
		String reSrc = null;
		try {
			reSrc = DESHelper.decryptFromHex(desc, key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("reSrc：\n" + reSrc);
	}

}
