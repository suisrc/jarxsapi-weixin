/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 *
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */
package com.suisrc.weixin.mp.crypto;

import java.util.ArrayList;

public class ByteGroup {
	
	private ArrayList<Byte> byteContainer = new ArrayList<>();

	public byte[] toBytes() {
		byte[] bytes = new byte[this.byteContainer.size()];
		for (int i = 0; i < this.byteContainer.size(); i++) {
			bytes[i] = this.byteContainer.get(i);
		}
		return bytes;
	}

	public ByteGroup addBytes(byte[] bytes) {
		for (byte b : bytes) {
			this.byteContainer.add(b);
		}
		return this;
	}

	public int size() {
		return this.byteContainer.size();
	}
}
