/**
 * File Name: IndexController.java
 * Date: 2019-10-15 10:02:32
 */
package me.belucky.toolscenter.controller;

import com.jfinal.kit.PropKit;

/**
 * Description: 首页控制器
 * @author shenzulun
 * @date 2019-10-15
 * @version 1.0
 */
public class IndexController extends JsonLogController{

	public Class<?> setObj() {
		return null;
	}

	public void go(Object dto, String methodName) {
		invoke(this, methodName, dto);
	}
	
	public void hello() {
		renderText("hello World");
	}
	
	/**
	 * 首页路由
	 */
	public void index() {
		render("index.html");
	}

	public void getUrl() {
		String sysid = this.getPara("sysid");
		String sysurl = PropKit.getProp("url-config.properties").get(sysid);
		renderJson("sysurl",sysurl);
	}
	
	/**
	 * 刷新缓存
	 */
	public void refresh(){
		String propNames = getPara("propNames");  //prop_name
		if(propNames != null){
			String[] arr = propNames.split(",");
			for(String propName : arr){
				PropKit.useless(propName);
				PropKit.use(propName);
			}
		}
		renderJson("message","success");
	}
	
}
