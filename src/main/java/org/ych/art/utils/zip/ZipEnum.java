package org.ych.art.utils.zip;

/**
 * 消息类型，连接、发送、断开、检测、应答
 * 
 * @author 姚赪海
 *
 */
public enum ZipEnum {

	/**
	 * NONE -- 不压缩
	 */
	NONE((byte) 0, "不压缩"), // NONE

	/**
	 * ZIP--压缩稍快，压缩比不好
	 */
	ZIP((byte) 1, "ZIP"), // ZIP

	/**
	 * bZip2 -- 压缩非常慢
	 */
	BZIP2((byte) (1 << 1), "bZip2"), // bZip2

	/**
	 * gZip-- 综合性能最好，建议使用
	 */
	GZIP((byte) (1 << 2), "gZip"), // gZip

	/**
	 * jzlib --压缩比空，但方法过时
	 */
	JZLIB((byte) (1 << 3), "jzlib"),;// jzlib

	private byte id;
	private String name;

	private ZipEnum(byte id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the index
	 */
	public byte getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public static ZipEnum fromId(byte id) {
		for (ZipEnum p : ZipEnum.values()) {
			if (id == p.id)
				return p;
		}
		return null;
	}

	public byte[] zip(byte[] src) {

		byte[] desc = null;
		switch (this) {
		case NONE: // NONE
			desc = src;
			break;

		case ZIP:// ZIP
			desc = ZipUtil.zip(src);
			break;

		case BZIP2: // bZip2
			desc = ZipUtil.bZip2(src);
			break;

		case GZIP: // gZip
			desc = ZipUtil.gZip(src);
			break;

		case JZLIB:// jzlib
			desc = ZipUtil.jzlib(src);
		}

		return desc;
	}

	public byte[] unZip(byte[] src) {

		byte[] desc = null;
		switch (this) {
		case NONE: // NONE
			desc = src;
			break;

		case ZIP:// ZIP
			desc = ZipUtil.unZip(src);
			break;

		case BZIP2: // bZip2
			desc = ZipUtil.unBZip2(src);
			break;

		case GZIP: // gZip
			desc = ZipUtil.unGZip(src);
			break;

		case JZLIB:// jzlib
			desc = ZipUtil.unJzlib(src);
		}

		return desc;
	}

}