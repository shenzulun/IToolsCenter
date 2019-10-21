/**
 * File Name: HIPCrawlerHandler.java
 * Date: 2019-10-16 10:01:20
 */
package me.belucky.toolscenter.core.crawler.impl.hip;

import java.util.Map;

import me.belucky.easytool.util.CollectionUtils;
import me.belucky.toolscenter.core.crawler.AbstractCrawlerHandler;

/**
 * Description: 
 * @author shenzulun
 * @date 2019-10-16
 * @version 1.0
 */
public class HIPCrawlerHandler extends AbstractCrawlerHandler{
	
	public HIPCrawlerHandler(){
		super("crawler/hip-config.properties");
		Map<String, String> params = CollectionUtils.buildMap("carPlate", "carPlate");
		params.put("opPwd", "11");
		init(params);
	}
}
