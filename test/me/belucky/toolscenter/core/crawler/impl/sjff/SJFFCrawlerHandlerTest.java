/**
 * File Name: SJFFCrawlerHandlerTest.java
 * Date: 2019-10-16 10:24:10
 */
package me.belucky.toolscenter.core.crawler.impl.sjff;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.jfinal.kit.PropKit;
import me.belucky.toolscenter.util.CommonUtils;

/**
 * Description: 
 * @author shenzulun
 * @date 2019-10-16
 * @version 1.0
 */
public class SJFFCrawlerHandlerTest {
	
	@Before
	public void before() {
		PropKit.useFirstFound("sys-config.properties");
		CommonUtils.initPropIgnore("", "sys-config.properties");
		CommonUtils.initProp("crawler");
	}
	
	@Test
	public void test1() {
		SJFFCrawlerHandler crawler = new SJFFCrawlerHandler();
		List<String> result = crawler.handle(null);
		System.out.println(result.get(0));
	}

}
