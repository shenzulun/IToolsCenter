/**
 * File Name: ETCDBUtilTest.java
 * Date: 2019-10-16 15:36:16
 */
package me.belucky.toolscenter.util;

import org.junit.Before;
import org.junit.Test;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import me.belucky.toolscenter.LaunchEntry;
import me.belucky.toolscenter.model.table._MappingKit;


/**
 * Description: 
 * @author shenzulun
 * @date 2019-10-16
 * @version 1.0
 */
public class ETCDBUtilTest {

	@Before
	public void before() {
		DruidPlugin druidPlugin = LaunchEntry.createDruidPlugin();
		druidPlugin.start();
		
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		arp.setShowSql(false);
		arp.setDialect(new AnsiSqlDialect());
		_MappingKit.mapping(arp);
		arp.start();
		
	}
	
	@Test
	public void test1() throws Exception {
		ETCDBUtil.go();
	}
	
	public static void main(String[] args) throws Exception {
		ETCDBUtilTest test = new ETCDBUtilTest();
		test.before();
		
		test.test1();
		
		Thread.sleep(1000 * 60 * 60 * 24);
	}
	
}
