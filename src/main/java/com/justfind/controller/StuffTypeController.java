package com.justfind.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.justfind.dao.SecondTypeMapper;
import com.justfind.dao.StuffTypeMapper;
import com.justfind.entity.SecondType;
import com.justfind.entity.StuffType;
import com.justfind.view.Message;

/**
 * 材料分类相关的Controller
 * 
 * @author chebao
 *
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/stuffType")
public class StuffTypeController extends BaseController {

	@Autowired
	private StuffTypeMapper stuffTypeMapper;

	@Autowired
	private SecondTypeMapper secondTypeMapper;

	/**
	 * 查询材料分类接口
	 * 
	 * @param stuff
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryAllType", method = RequestMethod.POST)
	public ResponseEntity<Message> queryAllType(HttpServletResponse response) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*"); // 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		List<StuffType> list = stuffTypeMapper.selectAll();
		return buildReturnEntity(new Message(0, "ok", list));
	}

	/**
	 * 
	 * @param stuff
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listAllType", method = RequestMethod.POST)
	public ResponseEntity<Message> listAllType(HttpServletResponse response) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*"); // 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		List<StuffType> list = stuffTypeMapper.selectAll();
		return buildReturnEntity(new Message(0, "ok", list));
	}

	@RequestMapping(value = "/listSecondType", method = RequestMethod.POST)
	public ResponseEntity<Message> listSecondType(Integer stuffTypeId, HttpServletResponse response)
			throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*"); // 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		List<SecondType> list = secondTypeMapper.selectByStuffTypeId(stuffTypeId);
		return buildReturnEntity(new Message(0, "ok", list));
	}

	@RequestMapping(value = "/getProductList", method = RequestMethod.POST)
	public ResponseEntity<Message> getProductList(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*"); // 允许所有来源访问
		response.setHeader("Access-Control-Allow-Headers", "Authentication");
		List<StuffType> list = stuffTypeMapper.getProductList();
		return buildReturnEntity(new Message(0, "ok", list));
	}

}
