/**
 * File Name: CommonUtils.java
 * Date: 2019-10-15 10:22:31
 */
package me.belucky.toolscenter.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jfinal.kit.PropKit;
import me.belucky.easytool.config.ConstCode;
import me.belucky.easytool.parser.ParserEnum;
import me.belucky.easytool.parser.ParserFactory;
import me.belucky.easytool.task.TaskInitCenter;
import me.belucky.easytool.util.FileTools;
import me.belucky.toolscenter.core.crawler.impl.etc.ETCCrawlerHandler;
import me.belucky.toolscenter.core.crawler.impl.etc.api.ETCQueryResponseBody;
import me.belucky.toolscenter.core.crawler.impl.etc.api.ETCQueryResponseDoc;
import me.belucky.toolscenter.core.crawler.impl.etc.api.ETCLogonResponseDoc;

/**
 * Description: 通用工具类
 * @author shenzulun
 * @date 2019-10-15
 * @version 1.0
 */
public class CommonUtils extends me.belucky.easytool.util.CommonUtils{
	protected static Logger log = LoggerFactory.getLogger(CommonUtils.class);
	private static volatile boolean taskInitFlag = false;         //任务初始化标志
	private static List<String> passwordPool = null;
	private static int passwordPoolSize = -1;
	
	static {
		passwordPool = FileTools.getContentList("password_pool.txt", false, "UTF-8");
		passwordPoolSize = passwordPool.size();
	}
	
	/**
	 * 程序初始化任务
	 */
	public static void init() {
		try {
			if(!taskInitFlag){
				taskInitFlag = true;
				TaskInitCenter.go();
				ETCDBUtil.go();
				log.info("系统初始化成功...");
			}
		} catch (Exception e) {
			log.error("系统初始化失败...",e);
		}
	}
	
	/**
	 * 批量初始化prop目录
	 * @param propFolderName
	 */
	public static void initProp(String... propFolderNames){
		//类似遍历resources/logAnalyse目录
		for(String propFolderName : propFolderNames){
			String path = Thread.currentThread().getContextClassLoader().getResource(propFolderName).getFile();
			String[] arr = FileTools.getFileNameArray(path, ".properties");
			for(String s : arr){
				PropKit.use(propFolderName + "/" + s);
			}
		}
	}
	
	/**
	 * 批量初始化prop目录
	 * @param propFolderName prop目录
	 * @param ignorePropName 忽略的prop文件名
	 */
	public static void initPropIgnore(String propFolderName, String ignorePropName){
		String prePath = "";
		if(!"".contentEquals(propFolderName)) {
			prePath = propFolderName + "/";
		}
		String path = Thread.currentThread().getContextClassLoader().getResource(propFolderName).getFile();
		String[] arr = FileTools.getFileNameArray(path, ".properties");
		for(String s : arr){
			if(!s.equals(ignorePropName)) {
				PropKit.use(prePath + s);
				log.info("配置文件[{}]加载成功...", s);
			}
		}
	}
	
	public static String toJson(Object object) {
		String result = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			result = objectMapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			log.error("", e);
		} catch (JsonMappingException e) {
			log.error("", e);
		} catch (IOException e) {
			log.error("", e);
		}
		return result;
	}
	
	public static Map jsonToMap(String jsonStr) {
		Map map = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			map = objectMapper.readValue(jsonStr, Map.class);
		} catch (JsonParseException e) {
			log.error("", e);
		} catch (JsonMappingException e) {
			log.error("", e);
		} catch (IOException e) {
			log.error("", e);
		}
		return map;
	}
	
	public static String queryIDCardsByCarPlatesFromETC(String carPlates, String opPwd) {
		String result = "";
		StringBuffer buff = new StringBuffer();
		if(carPlates != null){
			String[] arr = carPlates.split("\n");
			if(arr != null){
				ETCCrawlerHandler crawler = new ETCCrawlerHandler(carPlates, opPwd);
				for(int i = 0,len = arr.length;i < len;i++){
					List<String> list = crawler.handle(arr[i]);
					if(list != null && list.size() > 0) {
						buff.append(list.get(0)).append(ConstCode.LINE_BREAK);
					}
				}
			}
		}
		result = buff.toString();
		return result;
	}
	
	public static String queryIDCardByCarPlateFromETC(String carPlate, String opPwd) {
		String result = null;
		ETCCrawlerHandler crawler = new ETCCrawlerHandler(carPlate, opPwd);
		List<String> list = crawler.handle(carPlate);
		if(list != null && list.size() > 0) {
			result = list.get(0);
		}
		return result;
	}
	
	public static String generatePassword() {
		Random rand = new Random();
		int r = rand.nextInt(passwordPoolSize);
		String result = passwordPool.get(r);
		return result;
	}
	
}

