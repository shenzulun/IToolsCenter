/**
 * File Name: CrawlerController.java
 * Date: 2019-10-15 14:24:10
 */
package me.belucky.toolscenter.controller;

import java.util.Map;

import com.jfinal.core.ActionKey;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.PropKit;
import me.belucky.toolscenter.dto.MsgDTO;
import me.belucky.toolscenter.util.CommonUtils;

/**
 * Description: 爬虫
 * @author shenzulun
 * @date 2019-10-15
 * @version 1.0
 */
public class CrawlerController extends JsonLogController{

	public Class<?> setObj() {
		return null;
	}

	public void go(Object dto, String methodName) {
		invoke(this, methodName, dto);
	}
	
	@ActionKey("/tool/etcQuery")
	public void toQueryETC() {
		setAttr("password_old", PropKit.getProp("crawler/etc-config.properties").get("password"));
		render("etcQuery.html");
	}
	
	/**
	 * 爬取ETC管理系统的身份证号码
	 */
	@ActionKey("/tool/etcQuery/go")
	public void queryETC() {
		MsgDTO msgDto = new MsgDTO();		
		String jsonStr = HttpKit.readData(getRequest());
//		String reqMsg = getPara("reqMsg");  
//		String opPwd = getPara("opPwd");
		Map<String, String> map = CommonUtils.jsonToMap(jsonStr);
		log.info("车牌号查询: {}", map.get("reqMsg"));
		String result = CommonUtils.queryIDCardsByCarPlatesFromETC(map.get("reqMsg"), map.get("opPwd"));
		msgDto.setRetMsg(result);
		renderJson(msgDto);
	}

}
