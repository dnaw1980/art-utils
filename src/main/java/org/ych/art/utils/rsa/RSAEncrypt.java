package org.ych.art.utils.rsa;

import static org.ych.art.utils.rsa.KeyStringPair.RSA;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

//import lombok.extern.slf4j.Slf4j;


//@Slf4j
public class RSAEncrypt {

	/**
	 * 加密源串最大长度
	 */
	protected static final int SRC_MAX_BYTE = 117;
	
	protected static final int DESC_MAX_BYTE = 128;

//	private static final Logger LOGGER = Logger.getLogger(RSAEncrypt.class);

	/**
	 * 生成密钥对
	 * 
	 * @return
	 */
	public static KeyPair genKeyPair() {
		// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
		KeyPairGenerator keyPairGen = null;

		final int MAX_KEY_SIZE = 1024;
		try {
			keyPairGen = KeyPairGenerator.getInstance(RSA);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
//			LOGGER.error("错误", e);
		}
		// 初始化密钥对生成器，密钥大小为96-1024位
		keyPairGen.initialize(MAX_KEY_SIZE, new SecureRandom());
		// 生成一个密钥对，保存在keyPair中
		KeyPair keyPair = keyPairGen.generateKeyPair();

		return keyPair;
	}

	/**
	 * 生成字符串型密钥对
	 * 
	 * @return
	 */
	public static KeyStringPair genKeyStringPair() {
		return new KeyStringPair(genKeyPair());
	}

	/**
	 * 把源串按 SRC_MAX_BYTE 拆解成 List
	 * 
	 * @param srcBytes
	 * @return
	 */
	private static List<byte[]> srcByteSplit(byte[] srcBytes) {

		int listSize = srcBytes.length / SRC_MAX_BYTE;
		int lastByteSize = srcBytes.length % SRC_MAX_BYTE;
		listSize += lastByteSize == 0 ? 0 : 1;

		List<byte[]> byteList = new ArrayList<byte[]>(listSize);
		for (int i = 0; i != listSize; i++) {

			byte[] tmpByte = null;
			if (i != listSize - 1) {
				// 不是最后一个
				tmpByte = new byte[SRC_MAX_BYTE];
				// 复制字节到目标中
				System.arraycopy(srcBytes, i * SRC_MAX_BYTE, tmpByte, 0, SRC_MAX_BYTE);

			} else {
				// 最后一个
				if (lastByteSize == 0) {
					lastByteSize = SRC_MAX_BYTE;
				}
				tmpByte = new byte[lastByteSize];
				// 复制字节到目标中
				System.arraycopy(srcBytes, i * SRC_MAX_BYTE, tmpByte, 0, lastByteSize);

			}

			byteList.add(tmpByte);

		}

		return byteList;

	}

	/**
	 * 把密串按 [长度字节][内容字节]的格式 拆解成 List
	 * 改成 
	 * 把密串按 [内容字节]的格式 拆解成 List
	 * 因为长度是 128
	 * 
	 * @param codeBytes
	 * @return
	 */
	private static List<byte[]> descByteSplit(byte[] codeBytes) {

		// 这个 list Size 这确定完全与结果相等
		int codeByteLength = codeBytes.length;
		int listSize = codeByteLength / DESC_MAX_BYTE;
		int lastByteSize = codeByteLength % DESC_MAX_BYTE;
		listSize += lastByteSize == 0 ? 0 : 1;

		List<byte[]> byteList = new ArrayList<byte[]>(listSize);

		for (int i = 0; i < codeByteLength;) { // 去掉后面的自增
			// 第一个字节是长度
//			int subLen = codeBytes[i] & 0x00ff;
			// 创建一个目标数组
			byte[] tmpByte = new byte[DESC_MAX_BYTE];
//			i++; // 现在 i 指向数据串的第一个位置
			System.arraycopy(codeBytes, i, tmpByte, 0, DESC_MAX_BYTE);
			// 向移动一个子长度，指向下一个长度字节位置
			i += DESC_MAX_BYTE;
			byteList.add(tmpByte);
		}

		return byteList;

	}

	/**
	 * 公钥加密过程
	 * 
	 * @param publicKey
	 *            公钥
	 * @param plainText
	 *            明文数据
	 * @return 密文
	 * @throws RSAException
	 *             加密过程中的异常信息
	 */
	public static String encryptPublic(String publicKey, String plainText) throws RSAException {

		KeyStringPair keyStringPair = new KeyStringPair();
		keyStringPair.setPublicKey(publicKey);
//		return Base64.encode(encryptCommon(keyStringPair.loadPublicKey(), plainText.getBytes()));
		return Base64.getEncoder().encodeToString(encryptCommon(keyStringPair.loadPublicKey(), plainText.getBytes()));
	}

	/**
	 * 公钥加密过程
	 * 
	 * @param publicKey
	 *            公钥
	 * @param plainTextData
	 *            明文数据
	 * @return 密文
	 * @throws RSAException
	 *             加密过程中的异常信息
	 */
	public static byte[] encryptPublic(String publicKey, byte[] plainTextData) throws RSAException {
		KeyStringPair keyStringPair = new KeyStringPair();
		keyStringPair.setPublicKey(publicKey);
		return encryptCommon(keyStringPair.loadPublicKey(), plainTextData);
	}

	/**
	 * 私钥加密过程
	 * 
	 * @param privateKey
	 *            私钥
	 * @param plainText
	 *            明文数据
	 * @return 密文
	 * @throws RSAException
	 *             加密过程中的异常信息
	 */
	public static String encryptPrivate(String privateKey, String plainText) throws RSAException {
		KeyStringPair keyStringPair = new KeyStringPair();
		keyStringPair.setPrivateKey(privateKey);
//		return Base64.encode(encryptCommon(keyStringPair.loadPrivateKey(), plainText.getBytes()));
		return Base64.getEncoder().encodeToString(encryptCommon(keyStringPair.loadPrivateKey(), plainText.getBytes()));
	}

	/**
	 * 私钥加密过程
	 * 
	 * @param privateKey
	 *            私钥
	 * @param plainTextData
	 *            明文数据
	 * @return 密文
	 * @throws RSAException
	 *             加密过程中的异常信息
	 */
	public static byte[] encryptPrivate(String privateKey, byte[] plainTextData) throws RSAException {
		KeyStringPair keyStringPair = new KeyStringPair();
		keyStringPair.setPrivateKey(privateKey);
		return encryptCommon(keyStringPair.loadPrivateKey(), plainTextData);
	}

	/**
	 * 公钥加密过程
	 * 
	 * @param publicKey
	 *            公钥
	 * @param plainTextData
	 *            明文数据
	 * @return 密文
	 * @throws RSAException
	 *             加密过程中的异常信息
	 */
	public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws RSAException {
		return encryptCommon(publicKey, plainTextData);
	}

	/**
	 * 私钥加密过程
	 * 
	 * @param privateKey
	 *            私钥
	 * @param plainTextData
	 *            明文数据
	 * @return 密文
	 * @throws RSAException
	 *             加密过程中的异常信息
	 */
	public static byte[] encrypt(RSAPrivateKey privateKey, byte[] plainTextData) throws RSAException {
		return encryptCommon(privateKey, plainTextData);
	}

	/**
	 * 加密过程
	 * 
	 * @param key
	 *            密钥
	 * @param plainTextData
	 *            明文数据
	 * @return 密文
	 * @throws RSAException
	 *             加密过程中的异常信息
	 */
	public static byte[] encryptCommon(Key key, byte[] plainTextData) throws RSAException {
		if (key == null) {
			throw new RSAException("密钥为空, 请设置");
		}
		Cipher cipher = null;
		try {

			// 使用默认RSA
			cipher = Cipher.getInstance(RSA);
			cipher.init(Cipher.ENCRYPT_MODE, key);

			int totalSize = 0;

			// 处理源字节串长度
			List<byte[]> plainTextDataList = srcByteSplit(plainTextData);
			List<byte[]> descByteList = new ArrayList<byte[]>(plainTextDataList.size());

			Iterator<byte[]> byteIt = plainTextDataList.iterator();
			while (byteIt.hasNext()) {
				byte[] bs = (byte[]) byteIt.next();
//				totalSize++;

				byte[] output = cipher.doFinal(bs);
				descByteList.add(output);

				totalSize += output.length;

			}

			ByteBuffer bb = ByteBuffer.allocate(totalSize);
			byteIt = descByteList.iterator();
			while (byteIt.hasNext()) {
				byte[] bs = (byte[]) byteIt.next();

//				bb.put((byte) bs.length);
				bb.put(bs);

			}

			return bb.array();

		} catch (NoSuchAlgorithmException e) {
//			LOGGER.error("错误", e);
			e.printStackTrace();
			throw new RSAException("无此加密算法");
		} catch (NoSuchPaddingException e) {
//			LOGGER.error("错误", e);
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
//			LOGGER.error("错误", e);
			e.printStackTrace();
			throw new RSAException("加密公钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
//			LOGGER.error("错误", e);
			e.printStackTrace();
			throw new RSAException("明文长度非法");
		} catch (BadPaddingException e) {
//			LOGGER.error("错误", e);
			e.printStackTrace();
			throw new RSAException("明文数据已损坏");
		}
	}

	/**
	 * 公钥解密过程
	 * 
	 * @param publicKey
	 *            公钥
	 * @param cipherText
	 *            密文数据
	 * @return 明文
	 * @throws RSAException
	 *             加密过程中的异常信息
	 */
	public static String decryptPublic(String publicKey, String cipherText) throws RSAException {
		KeyStringPair keyStringPair = new KeyStringPair();
		keyStringPair.setPublicKey(publicKey);
//		return new String(decryptCommon(keyStringPair.loadPublicKey(), Base64.decode(cipherText)));
		return new String(decryptCommon(keyStringPair.loadPublicKey(), Base64.getDecoder().decode(cipherText)));
	}

	/**
	 * 公钥解密过程
	 * 
	 * @param publicKey
	 *            公钥
	 * @param cipherData
	 *            密文数据
	 * @return 明文
	 * @throws RSAException
	 *             加密过程中的异常信息
	 */
	public static byte[] decryptPublic(String publicKey, byte[] cipherData) throws RSAException {
		KeyStringPair keyStringPair = new KeyStringPair();
		keyStringPair.setPublicKey(publicKey);
		return decryptCommon(keyStringPair.loadPublicKey(), cipherData);
	}

	/**
	 * 私钥解密过程
	 * 
	 * @param privateKey
	 *            私钥
	 * @param cipherText
	 *            密文数据
	 * @return 明文
	 * @throws RSAException
	 *             加密过程中的异常信息
	 */
	public static String decryptPrivate(String privateKey, String cipherText) throws RSAException {
		KeyStringPair keyStringPair = new KeyStringPair();
		keyStringPair.setPrivateKey(privateKey);
//		return new String(decryptCommon(keyStringPair.loadPrivateKey(), Base64.decode(cipherText)));
		return new String(decryptCommon(keyStringPair.loadPrivateKey(), Base64.getDecoder().decode(cipherText)));
	}

	/**
	 * 私钥解密过程
	 * 
	 * @param privateKey
	 *            私钥
	 * @param cipherData
	 *            密文数据
	 * @return 明文
	 * @throws RSAException
	 *             加密过程中的异常信息
	 */
	public static byte[] decryptPrivate(String privateKey, byte[] cipherData) throws RSAException {
		KeyStringPair keyStringPair = new KeyStringPair();
		keyStringPair.setPrivateKey(privateKey);
		return decryptCommon(keyStringPair.loadPrivateKey(), cipherData);
	}

	/**
	 * 公钥解密过程
	 * 
	 * @param publicKey
	 *            公钥
	 * @param cipherData
	 *            密文数据
	 * @return 明文
	 * @throws RSAException
	 *             加密过程中的异常信息
	 */
	public static byte[] decrypt(RSAPublicKey publicKey, byte[] cipherData) throws RSAException {
		return decryptCommon(publicKey, cipherData);
	}

	/**
	 * 私钥解密过程
	 * 
	 * @param privateKey
	 *            私钥
	 * @param cipherData
	 *            密文数据
	 * @return 明文
	 * @throws RSAException
	 *             加密过程中的异常信息
	 */
	public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData) throws RSAException {
		return decryptCommon(privateKey, cipherData);
	}

	/**
	 * 解密过程
	 * 
	 * @param key
	 *            密钥
	 * @param cipherData
	 *            密文数据
	 * @return 明文
	 * @throws RSAException
	 *             解密过程中的异常信息
	 */
	public static byte[] decryptCommon(Key key, byte[] cipherData) throws RSAException {
		if (key == null) {
			throw new RSAException("解密私钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			// 使用默认RSA
			cipher = Cipher.getInstance(RSA);
			cipher.init(Cipher.DECRYPT_MODE, key);

			int totalSize = 0;

			List<byte[]> codeByteList = descByteSplit(cipherData);
			List<byte[]> descByteList = new ArrayList<byte[]>(codeByteList.size());

			Iterator<byte[]> byteIt = codeByteList.iterator();
			while (byteIt.hasNext()) {
				byte[] bs = (byte[]) byteIt.next();

				byte[] output = cipher.doFinal(bs);
				descByteList.add(output);

				totalSize += output.length;

			}

			ByteBuffer bb = ByteBuffer.allocate(totalSize);
			byteIt = descByteList.iterator();
			while (byteIt.hasNext()) {
				byte[] bs = (byte[]) byteIt.next();

				bb.put(bs);
			}

			return bb.array();

		} catch (NoSuchAlgorithmException e) {
//			LOGGER.error("错误", e);
			e.printStackTrace();
			throw new RSAException("无此解密算法");
		} catch (NoSuchPaddingException e) {
//			LOGGER.error("错误", e);
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
//			LOGGER.error("错误", e);
			e.printStackTrace();
			throw new RSAException("解密私钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
//			LOGGER.error("错误", e);
			e.printStackTrace();
			throw new RSAException("密文长度非法");
		} catch (BadPaddingException e) {
//			LOGGER.error("错误", e);
			e.printStackTrace();
			throw new RSAException("密文数据已损坏");
		}
	}

	/**
	 * 字节数据转十六进制字符串
	 * 
	 * @param data
	 *            输入数据
	 * @return 十六进制内容
	 */
	// public static String byteArrayToString(byte[] data) {
	// StringBuilder stringBuilder = new StringBuilder();
	// for (int i = 0; i < data.length; i++) {
	// // 取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
	// stringBuilder.append(HEX_CHAR[(data[i] & 0xf0) >>> 4]);
	// // 取出字节的低四位 作为索引得到相应的十六进制标识符
	// stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);
	// if (i < data.length - 1) {
	// stringBuilder.append(' ');
	// }
	// }
	// return stringBuilder.toString();
	// }
}
