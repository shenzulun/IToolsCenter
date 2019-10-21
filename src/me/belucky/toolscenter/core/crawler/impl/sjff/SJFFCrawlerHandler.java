/**
 * File Name: SJFFCrawlerHandler.java
 * Date: 2019-10-16 10:19:36
 */
package me.belucky.toolscenter.core.crawler.impl.sjff;

import java.util.Map;
import me.belucky.easytool.util.CollectionUtils;
import me.belucky.toolscenter.core.crawler.AbstractCrawlerHandler;

/**
 * Description: 
 * @author shenzulun
 * @date 2019-10-16
 * @version 1.0
 */
public class SJFFCrawlerHandler extends AbstractCrawlerHandler{
	
	public SJFFCrawlerHandler(){
		super("crawler/SJFF-config.properties");
		Map<String, String> params = CollectionUtils.buildMap("carPlate", "carPlate");
		params.put("opPwd", "11");
		init(params);
	}
}