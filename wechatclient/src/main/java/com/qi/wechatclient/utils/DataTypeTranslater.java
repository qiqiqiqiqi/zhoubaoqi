package com.qi.wechatclient.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Calendar;

// 数据转换器
public class DataTypeTranslater {
	public static final int INT_SIZE = 4;

	/**
	 *  int转byte[]
	 * @param number
	 * @return
	 * @throws IOException
	 */
	public static byte[] intToByte(int number) throws IOException {
		ByteArrayOutputStream boutput = new ByteArrayOutputStream();
		DataOutputStream doutput = new DataOutputStream(boutput);
		doutput.writeInt(number);
		return boutput.toByteArray();
	}
	
	/**
	 *  byte[4] 转int
	 * @param bytes
	 * @param offset
	 * @return
	 */
	public static int bytesToInt(byte[] bytes, int offset) {
		int value= 0;
	       for (int i = 0; i < 4; i++) {
	           int shift= (4 - 1 - i) * 8;
	           value +=(bytes[i + offset] & 0x000000FF) << shift;
	       }
	       return value;
	}
	
	private static ByteBuffer bbuf;
	/**
	 * float 转 byte[4]
	 * @param number
	 * @return
	 */
	public static byte[] floatToBytes(float number) {
		bbuf = ByteBuffer.allocate(4);
		bbuf.putFloat(number);  
		return bbuf.array();  
	}
	
	/**
	 * 文件转Byte[]
	 * @param address
	 * @return
	 * @throws IOException
	 */
	public static byte[] fileToByte(String address) throws IOException {
//		System.err.println("FilePath : " + address);
		
		File file = new File(address);
		long fileSize = file.length();

		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 获取字节数组长度
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}
		fi.close();
		return buffer;
	}
	
	/**
	 * 日期输出
	 * @param time
	 * @return
	 * @author Feng
	 */
	public static String getData(long time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		String t = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH)
				+ " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":"
				+ calendar.get(Calendar.SECOND);
		
		return t;
	}
}
