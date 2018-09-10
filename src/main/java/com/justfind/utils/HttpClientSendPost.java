package com.justfind.utils;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientSendPost {

	public static String sendHttpClientData(String url,String reuqestXml,String mchId) throws Exception{
		String resultStr="";
		 KeyStore keyStore  = KeyStore.getInstance("PKCS12");
		 String allPath=HttpClientSendPost.class.getClassLoader().getResource("/").getPath();
         FileInputStream instream = new FileInputStream(new File(allPath+"/apiclient_cert.p12"));//放退款证书的路径
         try {
             keyStore.load(instream, mchId.toCharArray());
         } finally {
             instream.close();
         }

         SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mchId.toCharArray()).build();
         SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                 sslcontext,
                 new String[] { "TLSv1" },
                 null,
                 SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
         CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
         try {

             HttpPost httpPost = new HttpPost(url);//退款接口
             
             System.out.println("executing request" + httpPost.getRequestLine());
             StringEntity  reqEntity  = new StringEntity(reuqestXml);
             // 设置类型
             reqEntity.setContentType("application/x-www-form-urlencoded");
             httpPost.setEntity(reqEntity);
             CloseableHttpResponse response = httpclient.execute(httpPost);
             try {
                 HttpEntity entity = response.getEntity();

                 System.out.println("----------------------------------------");
                 System.out.println(response.getStatusLine());
                 if (entity != null) {
                	 resultStr=EntityUtils.toString(entity);
                    
                 }
                 EntityUtils.consume(entity);
             } finally {
                 response.close();
             }
         } finally {
             httpclient.close();
         }
         return resultStr;
	}
	
	
}
