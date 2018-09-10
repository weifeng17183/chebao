package com.justfind.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.justfind.constant.BaseConstant;
import com.justfind.constant.ResultConst;
import com.justfind.entity.Users;
import com.justfind.utils.ImgTools;
import com.justfind.utils.ResponseHeaders;
import com.justfind.view.Message;

/**
 * 处理接口回复信息类和获取当前用户对象，获取Session
 * 
 * @author chebao
 *
 */
@Controller
public class BaseController {

	public static final String SUCCESS = "success";
	public static final String SUCCESS_MOBILE = "success_mobile";
	public static final String ERROR = "err";
	public static final String ERROR_MOBILE = "error_mobile";
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	@Value("#{configProperties['img.size']}")
	private Integer imgSize;

	@Value("#{configProperties['small.img.size']}")
	private Integer smallImgSize;

	public ResponseEntity<Message> buildReturnEntity(Message message) {
		return new ResponseEntity<Message>(message, ResponseHeaders.createResponseHeaders(), HttpStatus.OK);
	}

	public ResponseEntity<Message> buildSuccessReturnEntity(Object obj) {
		return buildReturnEntity(ResultConst.success(obj));
	}

	/**
	 * 在本地保存文件
	 * 
	 * @param path
	 * @param fileName
	 * @param multipartFile
	 * @throws Exception
	 */
	public void createPicAndChangeSize(String path, String fileName, MultipartFile multipartFile) throws Exception {
		String pathImg = createPic(path, fileName, multipartFile);
		ImgTools.changeSize(pathImg, pathImg, imgSize);
	}

	/**
	 * 在本地缩略文件
	 * 
	 * @param path
	 * @param fileName
	 * @param multipartFile
	 * @throws Exception
	 */
	public void createPicAndSmallSize(String path, String fileName, MultipartFile multipartFile) throws Exception {
		String pathImg = createPic(path, fileName, multipartFile);
		ImgTools.changeSize(pathImg, pathImg, smallImgSize);
	}

	private String createPic(String path, String fileName, MultipartFile multipartFile) {
		// 拼接文件的绝对路径
		String pathImg = path + fileName;
		// 创建文件
		File targetFile = new File(path);
		// 判断文件是否存在
		if (!targetFile.exists()) {
			// 不存在，就在生成一个目录
			targetFile.mkdirs();
		}
		// 保存
		try {
			// 保存文件到指定路径
			BufferedInputStream bin = new BufferedInputStream(multipartFile.getInputStream());
			BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(new File(path, fileName)));
			int b = 0;
			while ((b = bin.read()) != -1) {
				bout.write(b);
			}
			bout.flush();
			bin.close();
			bout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pathImg;
	}

	public String getIp(HttpServletRequest request) {
		String addr = request.getRemoteAddr();
		return addr;
	}

	/**
	 * 得到当前用户
	 * 
	 * @param request
	 * @return
	 */
	public Users getLoginUser(HttpServletRequest request) {
		Users users = (Users) request.getSession().getAttribute(BaseConstant.LOGIN_SESSION);
		return users;
	}

	/**
	 * 得到session
	 * 
	 * @param request
	 * @return
	 */
	public HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}

	/**
	 * 获取http请求的真实IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIPAddr(HttpServletRequest request) {
		if (request == null)
			return null;
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("WL-Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("HTTP_CLIENT_IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getRemoteAddr();
		if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip))
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException unknownhostexception) {
			}
		return ip;
	}

	/***
	 * <br>
	 * <b>功能：</b>获取不带参数request请求<br>
	 * <b>作者：</b>maxmin<br>
	 * <b>日期：</b> 2015年5月27日 <br>
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getNoRequestParam(HttpServletRequest request) throws Exception {
		StringBuffer info = new StringBuffer();
		InputStream in = request.getInputStream();
		BufferedInputStream buf = new BufferedInputStream(in);
		byte[] buffer = new byte[2048];
		int iRead;
		while ((iRead = buf.read(buffer)) != -1) {
			info.append(new String(buffer, 0, iRead, "UTF-8"));
		}
		return info.toString();
	}
}
