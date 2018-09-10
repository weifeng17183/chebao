package com.justfind.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.justfind.constant.BaseConstant;
import com.justfind.service.IVerifyCodeService;
import com.justfind.utils.DateUtil;
import com.justfind.vo.UserContext;
import com.justfind.vo.VerifyCodeVO;
@Service("IVerifyCodeService")
public class VerifyCodeServiceImpl implements IVerifyCodeService {

	// 注入值
		//@Value("${sms.username}")
		private String username;

		//@Value("${sms.password}")
		private String password;

		//@Value("${sms.apikey}")
		private String apiKey;

		//@Value("${sms.url}")
		private String url;


		/**
		 * 校验,验证码,和发送短信内容
		 */
		@Override
		public void sendVerifyCode(String phoneNumber) {
			// 判断当前是否能够发送短信
			// 从session中获取最后一次发送短信的时间
			VerifyCodeVO vc = UserContext.getCurrentVerifyCode();
			if (vc == null
					|| DateUtil.secondsBetween(new Date(), vc.getLastSenTime()) > 10) {
				// 正常发送验证码短信
				// 生成一个验证码;
				String verifyCode = UUID.randomUUID().toString().substring(0, 4);
				// 发送短信
				try {
					// 创建一个URL对象
					URL url = new URL(this.url);
					// 通过URL得到一个HTTPURLConnection连接对象;
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					// 拼接POST请求的内容
					StringBuilder content = new StringBuilder(100)
							.append("username=").append(username)
							.append("&password=").append(password)
							.append("&apikey=").append(apiKey).append("&mobile=")
							.append(phoneNumber).append("&content=")
							.append("验证码是:").append(verifyCode).append(",请在5分钟内使用");
					// 发送POST请求,POST或者GET一定要大写
					conn.setRequestMethod("POST");
					// 设置POST请求是有请求体的
					conn.setDoOutput(true);
					// 写入POST请求体
					conn.getOutputStream().write(content.toString().getBytes());
					// 得到响应流(其实就已经发送了)
					String response = StreamUtils.copyToString(
							conn.getInputStream(), Charset.forName("UTF-8"));
					if (response.startsWith("success:")) {
						// 发送成功
						// 把手机号码,验证码,发送时间装配到VO中并保存到session
						vc = new VerifyCodeVO();
						vc.setLastSenTime(new Date());
						vc.setMobileNum(phoneNumber);
						vc.setVerifyCode(verifyCode);
						UserContext.putVerifyCode(vc);
					} else {
						// 发送失败
						throw new RuntimeException();
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("短信发送失败!");
				}
			} else {
				throw new RuntimeException("发送过于频繁!");
			}
		}

		/**
		 * 验证手机号码,验证码,是否一致
		 */
		@Override
		public boolean verify(String phoneNumber, String verifyCode) {
			// 获线程中的vo对象,存放验证码相关的东西
			VerifyCodeVO vc = UserContext.getCurrentVerifyCode();
			// 判断是否发送了验证码
			if (vc != null
					&& vc.getMobileNum().equals(phoneNumber)
					&& vc.getVerifyCode().equalsIgnoreCase(verifyCode)
					&& DateUtil.secondsBetween(new Date(), vc.getLastSenTime()) <= BaseConstant.VERIFYCODE_VAILDATE_SECOND) { // 发送了
				return true;
			}
			return false;
		}


}
