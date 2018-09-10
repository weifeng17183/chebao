package com.justfind.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.justfind.constant.BaseConstant;
import com.justfind.dao.CarInfoMapper;
import com.justfind.dao.UserAddressMapper;
import com.justfind.entity.CarInfo;
import com.justfind.entity.UserAddress;
import com.justfind.entity.Users;
import com.justfind.exception.CheckServiceException;
import com.justfind.exception.ErrorCode;
import com.justfind.service.UsersService;
import com.justfind.utils.Md5Encrypt;
import com.justfind.view.Message;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
	@Autowired
	private UsersService usersService;

	@Autowired
	private CarInfoMapper carInfoMapper;

	@Autowired
	private UserAddressMapper userAddressMapper;

	@RequestMapping(value = "/registe", method = RequestMethod.POST)
	public ResponseEntity<Message> registe(Users users, String callback, HttpServletResponse response)
			throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		users.setUserType(0);
		usersService.insert(users);
		return buildReturnEntity(new Message(0, "ok", null));
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Message> login(HttpServletRequest request, Users users, HttpServletResponse response)
			throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");// 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		Users loginUser = usersService.login(users);
		request.getSession().setAttribute("loginUser", loginUser);
		return buildReturnEntity(new Message(0, "ok", loginUser));
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<Message> logout(HttpServletRequest request, Users users, HttpServletResponse response)
			throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");// 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		HttpSession session = request.getSession();
		session.removeAttribute(BaseConstant.LOGIN_SESSION);
		return buildReturnEntity(new Message(0, "ok", null));
	}

	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public ResponseEntity<Message> info(HttpServletRequest request, String openId) throws IOException {
		Users entity = new Users();
		if (openId != null) {
			entity = usersService.selectByOpenId(openId);
			if (entity == null) {
				throw new CheckServiceException(ErrorCode.PARAMETERS_ILLEGAL, "参数异常，用户不存在！");
			}
		} else {
			throw new CheckServiceException(ErrorCode.PARAMETERS_ILLEGAL, "参数异常，openId为空！");
		}
		return buildSuccessReturnEntity(entity);
	}

	@RequestMapping(value = "/infoByMobileNum", method = RequestMethod.POST)
	public ResponseEntity<Message> infoByMobileNum(HttpServletRequest request, String mobileNum,
			HttpServletResponse response) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");// 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		Users entity = new Users();
		if (mobileNum != null) {
			entity = usersService.selectByMobileNum(mobileNum);
			if (entity == null) {
				throw new CheckServiceException(ErrorCode.PARAMETERS_ILLEGAL, "参数异常，用户不存在！");
			}
		} else {
			throw new CheckServiceException(ErrorCode.PARAMETERS_ILLEGAL, "参数异常，手机号为空！");
		}
		return buildSuccessReturnEntity(entity);
	}

	/**
	 * 用户信息更新接口
	 * 
	 * @param request
	 * @param users
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ResponseEntity<Message> updateUser(HttpServletRequest request, Users users, HttpServletResponse response)
			throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");// 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		Users dbUser = usersService.selectByPrimaryKey(users.getUserId());
		Users isEistUser = usersService.selectByMobileNum(users.getMobileNum());
		if (isEistUser != null && !users.getMobileNum().equals(dbUser.getMobileNum())) {
			throw new CheckServiceException(ErrorCode.REGISTERED, users.getMobileNum() + "手机号已存在！");
		}
		usersService.updateByPrimaryKeySelective(users);
		return buildReturnEntity(new Message(0, "ok", null));
	}

	@RequestMapping(value = "/changePass", method = RequestMethod.POST)
	public ResponseEntity<Message> changePass(HttpServletRequest request, Users users, String rePassword)
			throws IOException {
		Users entity = usersService.selectByMobileNum(users.getMobileNum());
		users.setUserId(entity.getUserId());
		users.setMobileNum(null);
		rePassword = Md5Encrypt.md5(rePassword);
		rePassword = Md5Encrypt.md5(rePassword);
		if (!entity.getPassword().equals(rePassword)) {
			throw new CheckServiceException(ErrorCode.PASSWORD_ERROR, "原始密码错误！");
		}
		usersService.updatePass(users);
		return buildReturnEntity(new Message(0, "ok", null));
	}

	/**
	 * 
	 * @param
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getOpenId")
	public ResponseEntity<Message> getOpenId(HttpServletRequest request) throws IOException {
		String appid = "wx84e0a20ed2d40abc";
		String secret = "f228fc55c5cc73467b633c29de49b1f0";
		String code = request.getParameter("code");
		System.out.println("code:" + code);
		String openId = usersService.getOpenId(appid, secret, code);
		return buildReturnEntity(new Message(0, "ok", openId));
	}

	@RequestMapping(value = "/selectCarInfo", method = RequestMethod.POST)
	public ResponseEntity<Message> selectCarInfo(Integer userId, HttpServletResponse response) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");// 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		List<CarInfo> list = carInfoMapper.selectByUserId(userId);
		return buildReturnEntity(new Message(0, "ok", list));
	}

	@RequestMapping(value = "/addCarInfo", method = RequestMethod.POST)
	public ResponseEntity<Message> addCarInfo(HttpServletRequest request, CarInfo carInfo, Integer userId,
			HttpServletResponse response) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");// 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		carInfoMapper.insertSelective(carInfo);
		carInfo.setCarId(carInfoMapper.getMaxId());
		return buildReturnEntity(new Message(0, "ok", carInfo));
	}

	@RequestMapping(value = "/updateCarInfo", method = RequestMethod.POST)
	public ResponseEntity<Message> updateCarInfo(CarInfo carInfo, HttpServletResponse response) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");// 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		carInfoMapper.updateByPrimaryKeySelective(carInfo);
		return buildReturnEntity(new Message(0, "ok", null));
	}

	@RequestMapping(value = "/deleteCarInfo", method = RequestMethod.POST)
	public ResponseEntity<Message> deleteCarInfo(Integer carId, HttpServletResponse response) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");// 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		carInfoMapper.deleteByPrimaryKey(carId);
		return buildReturnEntity(new Message(0, "ok", null));
	}

	@RequestMapping(value = "/addAddress", method = RequestMethod.POST)
	public ResponseEntity<Message> addAddress(UserAddress userAddress, HttpServletResponse response)
			throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");// 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		userAddressMapper.insertSelective(userAddress);
		userAddress.setAddressId(userAddressMapper.getMaxId());
		return buildReturnEntity(new Message(0, "ok", userAddress));
	}

	@RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
	public ResponseEntity<Message> updateAddress(UserAddress userAddress, HttpServletResponse response)
			throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");// 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		userAddressMapper.updateByPrimaryKeySelective(userAddress);
		return buildReturnEntity(new Message(0, "ok", null));
	}

	@RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
	public ResponseEntity<Message> deleteAddress(Integer addressId, HttpServletResponse response) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");// 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		userAddressMapper.deleteByPrimaryKey(addressId);
		return buildReturnEntity(new Message(0, "ok", null));
	}

	@RequestMapping(value = "/selectAddress", method = RequestMethod.POST)
	public ResponseEntity<Message> selectAddress(Integer userId, HttpServletResponse response) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");// 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		List<UserAddress> list = userAddressMapper.selectByUserId(userId);
		return buildReturnEntity(new Message(0, "ok", list));
	}

	/**
	 * 
	 * @param
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/loginPage")
	public String loginPage(HttpServletRequest request) throws IOException {
		String appid = "wx84e0a20ed2d40abc";
		String secret = "e132f9f5453c95922786466fe81ed0c5";
		String code = request.getParameter("code");
		System.out.println("code:" + code);
		String openId = usersService.getOpenId(appid, secret, code);
		return "redirect:https://cbguanjia.com/CheBao/SecretLogin.html?openid=" + openId;
	}
}
