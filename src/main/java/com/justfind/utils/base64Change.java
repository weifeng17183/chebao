package com.justfind.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class base64Change {
	/**
	 * @param imgStr
	 *            base64编码字符串
	 * @param path
	 *            图片路径-具体到文件
	 */
	public static boolean generateImage(String imgStr, String path) {
		if (imgStr == null)
			return false;

		imgStr = imgStr.substring(imgStr.indexOf(",") + 1);
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// 解密
			byte[] b = decoder.decodeBuffer(imgStr);
			// 处理数据
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 图片转化成base64字符串
	public static String GetImageStr() {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		String imgFile = "C:\\a.jpg";// 待处理的图片
		// 地址也有写成"F:/deskBG/86619-107.jpg"形式的
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	public static void main(String[] args) {
		generateImage(
				"data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcUFhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/2wBDAQcHBwoIChMKChMoGhYaKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCj/wAARCAGwAjADASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD6koooqDIKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigBVbH0qRTkZFRhc5xzSB9vqaaLQrEZNJQCPTNG4elIVgooooJCiiigAoppkUE0BsrkUDsOooooEFFA9qKACigUUAFFFFABRSZFNMqg0DsPoqNJQxxipAQetAWCio5ZlTrTEukboG/EUBYnopAQRmmrIGYqOooAfRRRQIKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigApyd6bTlGQaRUdwztUmo0H8R4p/bBqEtu49K1NASdVcgYNSE5iynWo/JVQWYAYqGO8gfKo+CKkRZiVuhp+0+lUTe2w/wCW9C31sf8AluakmxbMYbO7rUK22d2TxTI54pGIWU5NTAGOMnqKAEClEOOTTrdiR+8FLG6sh45oc7Fy3ApgRzBc9T+dQCS2HeT5/wC6TWNq3i3RNKyby+2FQflYHk155r3x/wBBsC0VlLFNKOFDq3X/ADmgD2WMjHyk/jSk/wBz5q+aLn43eKLvIttItefTI/rVZfHfxFv2H2TQIZB6LKw/Wlcm59Qrz14qRG9TXy3FqPxYucqPDkYz/Esv/wBenlPi0g50Rf8Av7/9egOY+oAcdajlkQcPuHptr5eXVvirbb86DEcfeYzf/Xp0fxK+IujDzr3w5alA21WMn/16AR9MPCsg+RpCfepIYpIyPkz78V812f7Quv2vy6ro9pEO+0k4/Wt7TP2jNEnBGoXS27eir0popSPdzOFOJAVb2FOURyDOePavJ9N+O/hCchY9TEjH1Qiuz0Px3oeqIGtr+Nge2DQJHQyi4GfK2Ef7VNjErYEwH+6lIJmaLNsfMB/iqK3nN1LJbzbFlC7mC9cZpAXlK4wnOKgVOC8nye1cf488cWXgq0mlupwznAVTx6/4V80/EH9oG+1ffDpiCOM4GQSOn40wPsiS9hjGXdf++lqiniC0aXaJU/76FfnTe+MtZuZZJDf3CMxz8kjAd/f3qkPEmr5z/al7n2mb/GlqHvH6Zw3CSqGV02nvuqTr05r839J8ea1YzxO2oXTBPWVjn9a+gfhV8eFfy7fVpFj+ULtOTz3oA+nXkVMbjjNOVwc4OaydE12x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",
				"C:\\tool\\apache-tomcat-8.0.36\\webapps\\chebao\\WEB-INF\\images\\carPic\\20180503165341=.jpg");
	}
}
