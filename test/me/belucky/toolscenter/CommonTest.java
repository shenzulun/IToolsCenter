/**
 * File Name: CommonTest.java
 * Date: 2019-10-16 16:50:32
 */
package me.belucky.toolscenter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.junit.Test;

import me.belucky.easytool.util.FileTools;

/**
 * Description: 
 * @author shenzulun
 * @date 2019-10-16
 * @version 1.0
 */
public class CommonTest {
	
//	@Test
	public void test1() throws Exception {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		String jsName1 = "D:/Users/9623436/eclipse-workspace/IToolsCenter/WebContent/static/js/jsencrypt.js";
		String jsName2 = "D:/Users/9623436/eclipse-workspace/IToolsCenter/WebContent/static/js/custom.js";
		FileReader fr1 = new FileReader(jsName1);
		FileReader fr2 = new FileReader(jsName2);
		engine.eval(fr1);
		engine.eval(fr2);
		if(engine instanceof Invocable) {
			Invocable invocable = (Invocable)engine;
			System.out.println(invocable.invokeFunction("myEncrypt"));
		}

	}
	
	@Test
	public void test2() {
		List<String> cphList = FileTools.getContentList("D:/Users/9623436/Desktop/ETC匹配/ETC爬虫/20191020/source.txt", true, "UTF-8");
		int count = cphList.size();
		int threads = 4;
		int per = count / threads;
		for(int i=0;i<threads;i++) {
			int start = per * i;
			int end = per * (i + 1) - 1;
			List<String> unit = cphList.subList(start, end); 
			FileTools.exportFile("D:/Users/9623436/Desktop/ETC匹配/ETC爬虫/20191020/source_" + (i+1) + ".txt", unit, "UTF-8");
		}
	}

}
