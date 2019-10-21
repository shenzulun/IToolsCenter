/**
 * File Name: ETCDBUtil.java
 * Date: 2019-10-16 15:15:29
 */
package me.belucky.toolscenter.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;

import me.belucky.easytool.config.ConstCode;
import me.belucky.easytool.util.FileTools;
import me.belucky.toolscenter.core.crawler.impl.etc.ETCCrawlerHandler;
import me.belucky.toolscenter.model.table.TEtcInfo2;

/**
 * Description: 
 * @author shenzulun
 * @date 2019-10-16
 * @version 1.0
 */
public class ETCDBUtil {
	protected static Logger log = LoggerFactory.getLogger(ETCDBUtil.class);

	public static List<String> query() {
//		StringBuffer buff = new StringBuffer("SELECT '浙'||T1.CPH FROM SHENZL.T_ETC_INFO T1 WHERE T1.CPH NOT IN(SELECT T2.CPH FROM SHENZL.T_ETC_INFO_1 T2) ")
//							    .append(" AND CONCAT('浙',T1.CPH) NOT IN (SELECT T3.CPH FROM SHENZL.T_ETC_INFO_2 T3)");
//		List<String> list = Db.query(buff.toString());
		String sql = PropKit.getProp("crawler/etc-config.properties").get("source_sql");
		List<String> list = Db.query(sql);
		return list;
	}
	
	public static List<TEtcInfo2> crawler(List<String> list) throws Exception {
		long sleepTime = PropKit.getProp("crawler/etc-config.properties").getLong("sleep_time",2100L);
		int size = list.size();
		int per = 100;
		int max = size / per;
		int cnt = 1;
		int batchNo = 1;
		log.info("开始爬取ETC发行系统，共计{}笔数据...", size);
		List<TEtcInfo2> resultList = new ArrayList<TEtcInfo2>();
		String carPlates = list.get(0);
		ETCCrawlerHandler crawler = new ETCCrawlerHandler(carPlates, CommonUtils.generatePassword());
		for(String cph : list) {
			List<String> result = crawler.handle(cph);
			Thread.sleep(sleepTime);
			String jsonStr = result.get(0);
			TEtcInfo2 etc = new TEtcInfo2();
			etc.setCPH(cph);
			etc.setIDNO(crawler.crawlerCallback(result, null).get(0));
			etc.setJsonStr(jsonStr);
			resultList.add(etc);
			if(cnt++ % per == 0 || cnt >= size) {
				export(resultList, batchNo);
				saveToDb(resultList);
				log.info("第{}个批次完成，当前{}/{}", batchNo , batchNo, max);
				batchNo++;
				resultList = new ArrayList<TEtcInfo2>();
				crawler.close();
				crawler = new ETCCrawlerHandler(carPlates, CommonUtils.generatePassword());
			}
		}
		log.info("爬取结束...");
		return resultList;
	}
	
	public static void export(List<TEtcInfo2> resultList, int batchNo) {
		StringBuffer buff = new StringBuffer();
		String lineSeparator = ConstCode.LINE_SEPARATOR;
		for(TEtcInfo2 etc : resultList) {
			buff.append(etc.getCPH()).append("|").append(etc.getIDNO()).append("|").append(etc.getJsonStr()).append("$").append(lineSeparator);
		}
		Prop prop = PropKit.getProp("crawler/etc-config.properties");
		FileTools.exportFile(prop.get("base_file_path") + "crawler_output_" + batchNo + ".txt", buff.toString(), "UTF-8");
	}
	
	public static void saveToDb(List<TEtcInfo2> resultList) {
		Db.batchSave(resultList, 500);
	}
	
	public static void go() throws Exception {
		List<String> list = query();
		List<TEtcInfo2> resultList = crawler(list);
//		export(resultList);
//		saveToDb(resultList);
	}
	
	public static void go(int threads) throws Exception {
		List<String> list = query();
		//均分
		int per = list.size() / threads;
		for(int i=0;i<threads;i++) {
			int start = per * i;
			int end = per * (i + 1) -1;
			List<String> unit = list.subList(start, end);
			new Thread("crawler-" + i) {
				@Override
				public void run() {
					try {
						crawler(unit);
					} catch (Exception e) {
						log.error("", e);
					}
				}
			}.start();
		}
		
	}
		
}
