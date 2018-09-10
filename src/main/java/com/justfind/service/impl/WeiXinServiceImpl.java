package com.justfind.service.impl;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.justfind.entity.WeixinAccessToken;
import com.justfind.service.WeiXinService;
import com.justfind.utils.Common;

@Service("weiXinService")
public class WeiXinServiceImpl implements WeiXinService {
	

	@Value("#{configProperties['wxapp.id']}")
	private String appid;
	
	@Value("#{configProperties['wxapp.secret']}")
	private String secret;
	
	
    /** 
     * 获取微信公众号平台接口的ACCESS_TOKEN 
     * @return 
     */  
    public  String getWeixinAccessToken(){  
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext application = webApplicationContext.getServletContext();  
        if(application.getAttribute("tokenMap")!=null){  
            WeixinAccessToken tempToken=(WeixinAccessToken) application.getAttribute("tokenMap");  
            if(System.currentTimeMillis()>tempToken.getExpirationTime()){  
                return tempToken.getAccessToken();  
            }else{  
                return getAccessToken();  
            }  
        }else{  
            return getAccessToken();  
        }  
    }  
      
    private String getAccessToken() {  
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext application = webApplicationContext.getServletContext();  
        String appId=appid;  
        String appSecret=secret;  
        String url="https://api.weixin.qq.com/cgi-bin/token";  
        String returnData=Common.sendGet(url,"grant_type=client_credential&appid="+appId+"&secret="+appSecret);  
        JSONObject json=JSONObject.parseObject(returnData);  
        if(json.containsKey("access_token")){  
            if(json.get("access_token")!=null&&!json.get("access_token").equals("")){  
                application.setAttribute("tokenMap", new WeixinAccessToken(json.get("access_token").toString(),  
                        System.currentTimeMillis()+Integer.parseInt(json.get("expires_in").toString())));  
                return json.get("access_token").toString();  
            }
        }  
        return null;  
    }  

}
