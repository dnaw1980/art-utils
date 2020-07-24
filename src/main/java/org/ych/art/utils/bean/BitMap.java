package org.ych.art.utils.bean;

/**
 * 利用bit 保存一个逻辑数组 <br>
 * 节省空间，线程安全
 * 
 * @author 姚赪海
 *
 */
public class BitMap {

	/**
	 * 位图总大小
	 */
	private int size;

	/**
	 * 存储位用的整型数组，每个整型按32位计算
	 */
	private int[] bMap;

	public BitMap(int size) {
		if (size <= 0) {
			throw new RuntimeException("BitMap size 必须  > 0");
		}

		this.size = size;
		// 一个整形32位
		int arrLen = size / 32;
		if (size % 32 != 0) {
			arrLen++;
		}

		this.bMap = new int[arrLen];
		for (int i = 0; i != this.bMap.length; ++i) {
			this.bMap[i] = 0;
		}

	}

	/**
	 * 检查给定的下标是否合法
	 * 
	 * @param index
	 */
	private void checkIndex(int index) {
		if (index >= size) {
			throw new RuntimeException("BitMap 下标不能大于 size " + this.size);
		}

		if (index < 0) {
			throw new RuntimeException("BitMap 下标不能为负数");
		}
	}

	/**
	 * 根据给定下标判断该位在size 中的下标和偏移量
	 * 
	 * @param index
	 * @return
	 */
	private int[] getPos(int index) {
		
		int[] rsPos = new int[2];

		rsPos[0] = index / 32;
		rsPos[1] = index % 32;

		return rsPos;
	}

	/**
	 * 设置对应位的值
	 * 
	 * @param index
	 * @param value
	 */
	public void set(int index, boolean value) {
		this.checkIndex(index);

		int[] offer = this.getPos(index);
		int mark = 1 << offer[0];

		synchronized (this) {
			this.bMap[offer[0]] = value ? // 判断设置值
					mark | this.bMap[offer[0]] // 为真的情况
					: (~mark) & this.bMap[offer[0]];// 为假的情况
		}

	}

	/**
	 * 获取对应位的值
	 * 
	 * @param index
	 * @return
	 */
	public boolean get(int index) {
		this.checkIndex(index);

		int[] offer = this.getPos(index);
		boolean rs = false;
		synchronized (this) {
			rs = (this.bMap[offer[0]] & offer[1]) != 0;
		}

		return rs;
	}
}
