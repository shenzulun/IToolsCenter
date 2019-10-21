/**
 * File Name: ETCCrawlerHandler.java
 * Date: 2019-10-15 11:03:27
 */
package me.belucky.toolscenter.core.crawler.impl.etc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import me.belucky.easytool.parser.ParserEnum;
import me.belucky.easytool.parser.ParserFactory;
import me.belucky.easytool.util.CollectionUtils;
import me.belucky.toolscenter.core.crawler.AbstractCrawlerHandler;
import me.belucky.toolscenter.core.crawler.impl.etc.api.ETCLogonRequestBody;
import me.belucky.toolscenter.core.crawler.impl.etc.api.ETCLogonResponseBody;
import me.belucky.toolscenter.core.crawler.impl.etc.api.ETCQueryRequestBody;
import me.belucky.toolscenter.core.crawler.impl.etc.api.ETCQueryRequestDoc;
import me.belucky.toolscenter.core.crawler.impl.etc.api.ETCQueryResponseBody;
import me.belucky.toolscenter.core.crawler.impl.etc.api.ETCQueryResponseDoc;
import me.belucky.toolscenter.core.crawler.impl.etc.api.ETCLogonRequestDoc;
import me.belucky.toolscenter.core.crawler.impl.etc.api.ETCLogonResponseDoc;

/**
 * Description: 
 * @author shenzulun
 * @date 2019-10-15
 * @version 1.0
 */
public class ETCCrawlerHandler extends AbstractCrawlerHandler{

	public ETCCrawlerHandler(String carPlate, String opPwd){
		super("crawler/etc-config.properties");
		Map<String, String> params = CollectionUtils.buildMap("carPlate", carPlate);
		params.put("opPwd", opPwd);
		init(params);
	}
	
	/**
	 * 登录前处理
	 * @param logonResult
	 * @param params
	 */
	@Override
	public Object beforeLogon(Object reqObject, Map<String, String> params) {
		params.put("carPlate", String.valueOf(reqObject));
		ETCLogonRequestDoc req = new ETCLogonRequestDoc();
		req.setMethod("zjetc.issued.oplogon");
		req.setBiz_content(new ETCLogonRequestBody(params.get("userName"), params.get("opPwd")));
		return req;
	}
	
	/**
	 * 登录后回调
	 */
	@Override
	public void logonCallback(String logonResult, Map<String, String> params) {
		ETCLogonResponseDoc reqDto = new ETCLogonResponseDoc();
		logonResult = logonResult.replaceAll("\\\\", "");
		logonResult = logonResult.replace("\"{", "{").replace("}\"", "}");
		reqDto = ParserFactory.getParser(ParserEnum.JSON, reqDto.getClass()).parse(logonResult);
		params.put("ticket", reqDto.getBiz_content().getTicket());
	}
	
	/**
	 * 爬取前处理
	 * @param reqObject
	 * @param params
	 * @return
	 */
	@Override
	public Object beforeCrawler(Object reqObject, Map<String, String> params) {
		ETCQueryRequestDoc req = new ETCQueryRequestDoc();
		req.setMethod("zjetc.issued.driving-license.search");
		req.setTicket(params.get("ticket"));
		req.setBiz_content(new ETCQueryRequestBody(params.get("carPlate"), "0"));
		return req;
	}
	 
	/**
	 * 爬取后回调
	 * @param logonResult
	 * @param params
	 */
	@Override
	public List<String> crawlerCallback(List<String> result, Map<String, String> params) {
		List<String> list = new ArrayList<String>();
		if(result != null && result.size() > 0) {
			ETCQueryResponseDoc respDto = new ETCQueryResponseDoc();
			for(String str : result) {
				str = str.replaceAll("\\\\", "");
				if(str.contains("biz_content\":\"[]\"")) {
					str = str.replace("\"[", "[").replace("]\"", "]");
				}else {
					str = str.replace("\"[{", "[{").replace("}]\"", "}]");
				}
				respDto = ParserFactory.getParser(ParserEnum.JSON, respDto.getClass()).parse(str);
				List<ETCQueryResponseBody> queryList = respDto.getBiz_content();
				StringBuffer buff = new StringBuffer();
				if(queryList != null && queryList.size() > 0) {
					for(int i=0,len=queryList.size();i<len;i++) {
						if(i > 0) {
							buff.append(",");
						}
						buff.append(queryList.get(i).getVc_id_code());
					}
				}
				list.add(buff.toString());
			}
		}
		return list;
	}
}
