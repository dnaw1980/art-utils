package org.ych.art.utils.rsa;



import org.ych.art.utils.utils.Tools;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyStringPair implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static final String RSA = "RSA";

	private KeyPair keyPair;

	private String privateKey;
	// 得到公钥
	private String publicKey;

	public KeyStringPair() {
		super();
	}

	public KeyStringPair(KeyPair keyPair) {
		super();
		this.keyPair = keyPair;
		this.genKeyStringPair();
	}

	/**
	 * 使用密钥串生成密钥对
	 * 
	 * @throws Exception
	 */
	private void genKeyPair() throws RSAException {

		PublicKey publicKey = null;
		if (!Tools.empty(this.publicKey)) {
			try {
//				byte[] buffer = Base64.decode(this.publicKey);
				byte[] buffer = Base64.getDecoder().decode(this.publicKey);
				KeyFactory keyFactory = KeyFactory.getInstance(RSA);
				X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
				publicKey = keyFactory.generatePublic(keySpec);
			} catch (NoSuchAlgorithmException e) {
				throw new RSAException("无此算法");
			} catch (InvalidKeySpecException e) {
				throw new RSAException("公钥非法");
			} catch (NullPointerException e) {
				throw new RSAException("公钥数据为空");
			}
		}

		PrivateKey privateKey = null;
		if (!Tools.empty(this.privateKey)) {
			try {
//				byte[] buffer = Base64.decode(this.privateKey);
				byte[] buffer = Base64.getDecoder().decode(this.privateKey);
				PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
				KeyFactory keyFactory = KeyFactory.getInstance(RSA);
				privateKey = keyFactory.generatePrivate(keySpec);
			} catch (NoSuchAlgorithmException e) {
				throw new RSAException("无此算法");
			} catch (InvalidKeySpecException e) {
				throw new RSAException("私钥非法");
			} catch (NullPointerException e) {
				throw new RSAException("私钥数据为空");
			}
		}

		this.keyPair = new KeyPair(publicKey, privateKey);
	}

	/**
	 * 使用密钥对生成密钥串
	 */
	public void genKeyStringPair() {
		if (this.keyPair == null) {
			return;
		}

		// 得到私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		// 得到公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

		// 得到私钥字符串
//		this.privateKey = Base64.encode(privateKey.getEncoded());
		this.privateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());

		// 得到公钥字符串
//		this.publicKey = Base64.encode(publicKey.getEncoded());
		this.publicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
	}

	/**
	 * 加载公钥
	 * 
	 * @throws RSAException
	 *             加载公钥时产生的异常
	 */
	public RSAPublicKey loadPublicKey() throws RSAException {
		if (this.keyPair == null) {
			genKeyPair();
		}

		if (this.keyPair.getPublic() != null) {
			return (RSAPublicKey) this.keyPair.getPublic();
		}

		return null;

	}

	/**
	 * 加载私钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public RSAPrivateKey loadPrivateKey() throws RSAException {

		if (this.keyPair == null) {
			genKeyPair();
		}

		if (this.keyPair.getPrivate() != null) {
			return (RSAPrivateKey) this.keyPair.getPrivate();
		}

		return null;

	}

	/**
	 * @return the keyPair
	 */
	public KeyPair getKeyPair() {
		return keyPair;
	}

	/**
	 * @param keyPair
	 *            the keyPair to set
	 */
	public void setKeyPair(KeyPair keyPair) {
		this.keyPair = keyPair;
	}

	/**
	 * @return the privateKey
	 */
	public String getPrivateKey() {
		return privateKey;
	}

	/**
	 * @param privateKey
	 *            the privateKey to set
	 */
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	/**
	 * @return the publicKey
	 */
	public String getPublicKey() {
		return publicKey;
	}

	/**
	 * @param publicKey
	 *            the publicKey to set
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

}
