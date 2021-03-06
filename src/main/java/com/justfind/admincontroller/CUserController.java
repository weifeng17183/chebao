package com.justfind.admincontroller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.justfind.controller.BaseController;
import com.justfind.dao.CarImageMapper;
import com.justfind.dao.CarInfoMapper;
import com.justfind.dao.UsersMapper;
import com.justfind.entity.Admin;
import com.justfind.entity.CarInfo;
import com.justfind.entity.Users;
import com.justfind.service.UsersService;
import com.justfind.utils.PageContext;
import com.justfind.view.Message;

@Controller(value = "adminCUserController")
@RequestMapping(value = "/admin/cuser")
public class CUserController extends BaseController {
	@Autowired
	private UsersService usersService;

	@Autowired
	private UsersMapper usersMapper;

	@Autowired
	private CarInfoMapper carInfoMapper;

	@Autowired
	private CarImageMapper carImageMapper;

	public static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	@RequestMapping(value = "/insert")
	public String insert(HttpServletRequest request, Model model, Users users, CarInfo carInfo) {
		try {
			Integer count = usersMapper.selectExsitMobile(users);
			if (count > 0) {
				model.addAttribute("message", "手机号已存在！");
				return ERROR;
			}
			users.setRegisterDate(new Date());
			usersService.insert(users);
//			Users dbUser = usersService.selectByMobileNum(users.getMobileNum());
//			if (carInfo != null && carInfo.getCarNumber() != null) {
//				carInfo.setUserId(dbUser.getUserId());
//				carInfoMapper.insertSelective(carInfo);
//				String[] picPath = request.getParameterValues("falseInput");
//				for (String imgStr : picPath) {
//					String path = request.getSession().getServletContext().getRealPath("");
//					Integer carId = carInfoMapper.getMaxId();
//					CarImage entity = new CarImage();
//					entity.setCarId(carId);
//					String fileName = sdf1.format(new Date()) + ".jpg";
//					Thread.sleep(100);
//					String relativelyPath = "\\" + "images" + "\\" + BaseConstant.CAR_PIC + "\\";
//					path += "WEB-INF" + relativelyPath;
//					base64Change.generateImage(imgStr, path + fileName);
//					entity.setCarImageUrl(relativelyPath + fileName);
//					carImageMapper.insertSelective(entity);
//				}
//			}
			model.addAttribute("message", "添加成功！");
			model.addAttribute("redirectUrl", "admin/cuser/list?userType=0");
			return SUCCESS;
		} catch (Exception e) {
			model.addAttribute("message", "添加失败！");
			return ERROR;
		}
	}

	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, Model model, Users users, CarInfo carInfo) {
		try {
			usersService.updateByPrimaryKeySelective(users);
			if (users.getPassword() != null) {
				usersService.updatePass(users);
			}
			model.addAttribute("message", "修改成功！");
			model.addAttribute("redirectUrl", "admin/cuser/list?userType=0");
			return SUCCESS;
		} catch (Exception e) {
			model.addAttribute("message", "修改失败！");
			return ERROR;
		}
	}

	@RequestMapping(value = "/insertConductor")
	public String insertConductor(HttpServletRequest request, Model model, Users users) {
		try {
			Integer count = usersMapper.selectExsitMobile(users);
			if (count > 0) {
				model.addAttribute("message", "手机号已存在！");
				return ERROR;
			}
			users.setRegisterDate(new Date());
			usersService.insert(users);
			model.addAttribute("message", "添加成功！");
			model.addAttribute("redirectUrl", "admin/cuser/conductorList?userType=1");
			return SUCCESS;
		} catch (Exception e) {
			model.addAttribute("message", "添加失败！");
			return ERROR;
		}
	}

	@RequestMapping(value = "/saveConductor")
	public String saveConductor(HttpServletRequest request, Model model, Users users) {
		try {
			usersService.updateByPrimaryKeySelective(users);
			if (users.getPassword() != null) {
				usersService.updatePass(users);
			}
			model.addAttribute("message", "修改成功！");
			model.addAttribute("redirectUrl", "admin/cuser/conductorList?userType=1");
			return SUCCESS;
		} catch (Exception e) {
			model.addAttribute("message", "修改失败！");
			return ERROR;
		}
	}

	@RequestMapping(value = "/edit")
	public String edit(HttpServletRequest request, Model model, Integer userId) {
		Users users = usersService.selectByPrimaryKey(userId);
		model.addAttribute("user", users);
		return "cuser/edit";
	}

	@RequestMapping(value = "/info")
	public String info(HttpServletRequest request, Model model, Integer userId) {
		Users users = usersService.selectByPrimaryKey(userId);
		model.addAttribute("user", users);
		return "cuser/info";
	}

	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, Model model) {
		return "cuser/add";
	}

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model, Users users) {
		PageContext page = PageContext.getContext(request);
		Admin admin = (Admin) request.getSession().getAttribute("loginAdmin");
		users.setFactoryId(admin.getFactoryId());
		List<Users> userList = usersService.queryUserList(users);
		model.addAttribute("userList", userList);
		model.addAttribute("page", page);
		model.addAttribute("users", users);
		return "cuser/list";
	}

	@RequestMapping(value = "/editConductor")
	public String editConductor(HttpServletRequest request, Model model, Integer userId) {
		Users users = usersService.selectByPrimaryKey(userId);
		model.addAttribute("user", users);
		return "cuser/editConductor";
	}

	@RequestMapping(value = "/addConductor")
	public String addConductor(HttpServletRequest request, Model model) {
		return "cuser/addConductor";
	}

	@RequestMapping(value = "/conductorList")
	public String conductorList(HttpServletRequest request, Model model, Users users) {
		PageContext page = PageContext.getContext(request);
		Admin admin = (Admin) request.getSession().getAttribute("loginAdmin");
		users.setFactoryId(admin.getFactoryId());
		List<Users> userList = usersService.queryUserList(users);
		model.addAttribute("userList", userList);
		model.addAttribute("page", page);
		model.addAttribute("users", users);
		return "cuser/conductorList";
	}
	
	@RequestMapping(value = "/alert")
	public String alert(HttpServletRequest request, Model model, Integer userId) {
		Users users = usersService.selectByPrimaryKey(userId);
		List<CarInfo> carList = carInfoMapper.selectByUserId(userId);
		model.addAttribute("users", users);
		model.addAttribute("carList", carList);
		return "cuser/alert";
	}

	@RequestMapping(value = "/carInfo")
	@ResponseBody
	public ResponseEntity<Message> shopEditUser(HttpServletRequest request, Model model, Integer carId) {
		CarInfo carInfo = carInfoMapper.selectByPrimaryKey(carId);
		return buildSuccessReturnEntity(carInfo);
	}

	@RequestMapping(value = "/addCar")
	public String addCar(HttpServletRequest request, Model model, CarInfo carInfo) {
		try {
			if (StringUtils.isNotBlank(carInfo.getCarNumber())) {
				carInfoMapper.insertSelective(carInfo);
				model.addAttribute("redirectUrl", "admin/cuser/alert?userId=" + carInfo.getUserId());
				model.addAttribute("message", "添加成功！");
				return SUCCESS;
			}else {
				model.addAttribute("message", "添加失败，车牌号不能为空！");
				return ERROR;
			}
		} catch (Exception e) {
			model.addAttribute("message", "添加失败！");
			return ERROR;
		}
	}

	@RequestMapping(value = "/updateCar")
	public String updateCar(HttpServletRequest request, Model model, CarInfo carInfo) {
		try {
			if (StringUtils.isNotBlank(carInfo.getCarNumber())) {
				carInfoMapper.updateByPrimaryKeySelective(carInfo);
				model.addAttribute("redirectUrl", "admin/cuser/alert?userId=" + carInfo.getUserId());
				model.addAttribute("message", "修改成功！");
				return SUCCESS;
			}else {
				model.addAttribute("message", "添加失败，车牌号不能为空！");
				return ERROR;
			}
			
		} catch (Exception e) {
			model.addAttribute("message", "修改失败！");
			return ERROR;
		}
	}
}
