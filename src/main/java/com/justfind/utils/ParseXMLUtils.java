package com.justfind.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.util.StringUtils;
import org.xml.sax.InputSource;

import com.justfind.entity.PayBaseBean;
import com.justfind.entity.UnifiedOrder;

public class ParseXMLUtils {

	/**
	 * 1、DOM解析
	 */
	@SuppressWarnings("rawtypes")
	public static void beginXMLParse(String xml){
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML

			Element rootElt = doc.getRootElement(); // 获取根节点smsReport

			System.out.println("根节点是："+rootElt.getName());

			Iterator iters = rootElt.elementIterator("sendResp"); // 获取根节点下的子节点sms

			while (iters.hasNext()) {
				Element recordEle1 = (Element) iters.next();
				Iterator iter = recordEle1.elementIterator("sms");

				while (iter.hasNext()) {
					Element recordEle = (Element) iter.next();
					String phone = recordEle.elementTextTrim("phone"); // 拿到sms节点下的子节点stat值

					String smsID = recordEle.elementTextTrim("smsID"); // 拿到sms节点下的子节点stat值

					System.out.println(phone+":"+smsID);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 2、DOM4j解析XML（支持xpath）
	 * 解析的时候自动去掉CDMA
	 * @param xml
	 */
	public static void xpathParseXml(String xml){
		try { 
			StringReader read = new StringReader(xml);
			SAXReader saxReader = new SAXReader();
			Document doc = saxReader.read(read);
			String xpath ="/xml/appid";
			System.out.print(doc.selectSingleNode(xpath).getText());  
		} catch (DocumentException e) {
			e.printStackTrace();
		}  
	}

	/**
	 * 3、JDOM解析XML
	 * 解析的时候自动去掉CDMA
	 * @param xml
	 */
	@SuppressWarnings("unchecked")
	public static void jdomParseXml(String xml,UnifiedOrder unifiedorder){
		try { 
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			org.jdom.Document doc;
			doc = (org.jdom.Document) sb.build(source);

			org.jdom.Element root = doc.getRootElement();// 指向根节点
			List<org.jdom.Element> list = root.getChildren();
			if(list!=null&&list.size()>0){
				for (org.jdom.Element element : list) {
					if("return_code".equals(element.getName())){
						unifiedorder.setReturnCode(element.getText());
					}
					if("prepay_id".equals(element.getName())){
						unifiedorder.setPrepayId(element.getText());
					}
				}
			}

		} catch (JDOMException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 3、JDOM解析XML
	 * 解析的时候自动去掉CDMA
	 * @param xml
	 */
	@SuppressWarnings("unchecked")
	public static PayBaseBean jdomParseXml(String xml){
		PayBaseBean pbb=new PayBaseBean();
		try { 
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			org.jdom.Document doc;
			doc = (org.jdom.Document) sb.build(source);

			org.jdom.Element root = doc.getRootElement();// 指向根节点
			List<org.jdom.Element> list = root.getChildren();
			if(list!=null&&list.size()>0){
				for (org.jdom.Element element : list) {
					switch (element.getName()) {
					case "return_code":
						pbb.setReturnCode(element.getText());
						break;
					case "return_msg":
						pbb.setReturnMsg(element.getText());
						break;
					case "result_code":
						pbb.setResultCode(element.getText());
						break;
					case "err_code":
						pbb.setErrCode(element.getText());
						break;
					case "err_code_des":
						pbb.setErrCodeDes(element.getText());
						break;
					default:
						break;
					}
					
				}
			}

		} catch (JDOMException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return pbb;
	}
	
	
	

	public static boolean parseInt(String key){
		if(!StringUtils.isEmpty(key)){
			if(key.equals("total_fee")||key.equals("cash_fee")||key.equals("coupon_fee")||key.equals("coupon_count")||key.equals("coupon_fee_0")){
				return true;
			}
		}

		return false;
	}



	


}
