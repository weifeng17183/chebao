package com.justfind.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 微信的配置参数
 */
@SuppressWarnings("unused")
public class WeixinConfigUtils {

	private static final Log log = LogFactory.getLog(WeixinConfigUtils.class);


	public static String appid;
	public static String mch_id;
	public static String notify_url;

	static {

		try{
			InputStream is = WeixinConfigUtils.class.getResourceAsStream("/config.properties");
			Properties properties = new Properties();
			properties.load(is);
			
			appid = properties.getProperty("app_id");
			mch_id = properties.getProperty("mch_id");
			notify_url = properties.getProperty("notify_url");
		}catch(Exception ex){
			log.debug("加载配置文件："+ex.getMessage());
		}
	}


	public static void main(String[] args) throws IOException {
		InputStream is = WeixinConfigUtils.class.getResourceAsStream("/config.properties");

		Properties properties = new Properties();

		properties.load(is);

		is.close();
		String appid = properties.getProperty("appid");
		System.out.println(appid);
	}

}
