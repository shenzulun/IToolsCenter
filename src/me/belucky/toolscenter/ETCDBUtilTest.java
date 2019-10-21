/**
 * File Name: ETCDBUtilTest.java
 * Date: 2019-10-16 15:36:16
 */
package me.belucky.toolscenter;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import me.belucky.toolscenter.LaunchEntry;
import me.belucky.toolscenter.model.table._MappingKit;
import me.belucky.toolscenter.util.ETCDBUtil;


/**
 * Description: 
 * @author shenzulun
 * @date 2019-10-16
 * @version 1.0
 */
public class ETCDBUtilTest {

	
	public static void main(String[] args) throws Exception {
		DruidPlugin druidPlugin = LaunchEntry.createDruidPlugin();
		druidPlugin.start();
		
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		arp.setShowSql(false);
		arp.setDialect(new AnsiSqlDialect());
		_MappingKit.mapping(arp);
		arp.start();
		
		ETCDBUtil.go();
	}
}
