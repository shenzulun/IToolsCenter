/**
 * File Name: ETCCrawlerHandlerTest.java
 * Date: 2019-10-16 07:53:33
 */
package me.belucky.toolscenter.core.crawler.impl.etc;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import com.jfinal.kit.PropKit;

import me.belucky.easytool.parser.ParserEnum;
import me.belucky.easytool.parser.ParserFactory;
import me.belucky.toolscenter.core.crawler.impl.etc.api.ETCLogonResponseBody;
import me.belucky.toolscenter.core.crawler.impl.etc.api.ETCLogonResponseDoc;
import me.belucky.toolscenter.core.crawler.impl.etc.api.ETCQueryResponseDoc;
import me.belucky.toolscenter.core.crawler.impl.hip.HIPCrawlerHandler;
import me.belucky.toolscenter.util.CommonUtils;


/**
 * Description: 
 * @author shenzulun
 * @date 2019-10-16
 * @version 1.0
 */
public class ETCCrawlerHandlerTest {

	@Before
	public void before() {
		PropKit.useFirstFound("sys-config.properties");
		CommonUtils.initPropIgnore("", "sys-config.properties");
		CommonUtils.initProp("crawler");
	}
	
	@Test
	public void test0() {
		ETCCrawlerHandler crawler = new ETCCrawlerHandler("浙JH936C", CommonUtils.generatePassword());
		List<String> result = crawler.handle("浙JH936C");
		System.out.println(result.get(0));
		
		result = crawler.handle("浙J8PU50");
		System.out.println(result.get(0));
		crawler.close();
	}
	
//	@Test
	public void test1() {
		String result = CommonUtils.queryIDCardByCarPlateFromETC("浙JH936C", "H3CFncjqtPObcrQ6Ta75NwFfhQrSFHbxUam3y4oCy3S6EyDrUaDZj/uaHzNiAilxJJ56qGDH2QyguikrtsKDTYc7/rRJsnHzbKaVysmWlBbj/B30U2wNusfGGudHq12ogHtrNk3xC/wLcvkfHshMNYZWgz9Jj9JMmCQVcLvdEVU=");
		System.out.println(result);
	}
	
//	@Test
	public void test2() {
		String str = "{\"biz_content\":\"{\\\"agency\\\":\\\"0,1\\\",\\\"branch_name\\\":\\\"黄岩农合\\\",\\\"branch_no\\\":\\\"33100114\\\",\\\"op_code\\\":\\\"100068\\\",\\\"op_name\\\":\\\"黄岩农合\\\",\\\"rights\\\":\\\"1,3,5,6,8,9\\\",\\\"ticket\\\":\\\"A3796332192F7F90EF534A4F5D8ACC78\\\"}\",\"method\":\"zjetc.issued.oplogon\",\"return_code\":\"0\",\"return_msg\":\"操作成功！\"}\r\n";
		str = str.replaceAll("\\\\", "");
		str = str.replace("\"{", "{").replace("}\"", "}");
		ETCLogonResponseDoc reqDto = new ETCLogonResponseDoc();
		reqDto = ParserFactory.getParser(ParserEnum.JSON, reqDto.getClass()).parse(str);
		System.out.println(reqDto.getBiz_content().getTicket());
	}
	
//	@Test
//	public void test3() {
//		ETCResponseDTO<ETCLogonResponseDTO> reqDto = new ETCResponseDTO<ETCLogonResponseDTO>();
//		ETCLogonResponseDTO logon = new ETCLogonResponseDTO();
//		logon.setTicket("11111");
//		logon.setAgency("123sad");
//		logon.setOp_code("sad ");
//		reqDto.setMethod("sss");
//		reqDto.setBiz_content(logon);
//		System.out.println(CommonUtils.toJson(reqDto));
//	}
	
//	@Test
	public void test4() {
//		String str = "{\"biz_content\":\"[]\",\"method\":\"zjetc.issued.driving-license.search\",\"return_code\":\"0\",\"return_msg\":\"操作成功！\"}";
		String str = "{\"biz_content\":\"[{\\\"is_issued\\\":\\\"0\\\",\\\"is_signed\\\":\\\"0\\\",\\\"n_seats\\\":\\\"5\\\",\\\"n_weight\\\":\\\"\\\",\\\"vc_address\\\":\\\"浙江省台州市黄岩区东城街道红三村墙里229号[临1号]\\\",\\\"vc_engine\\\":\\\"1*****Z\\\",\\\"vc_id_code\\\":\\\"341224198503295611\\\",\\\"vc_owner_name\\\":\\\"于大星\\\",\\\"vc_type\\\":\\\"小型轿车\\\",\\\"vc_use_type\\\":\\\"非营运\\\",\\\"vc_vehicle_code\\\":\\\"浙JU170R\\\",\\\"vc_vin\\\":\\\"LG*************65\\\"}]\",\"method\":\"zjetc.issued.driving-license.search\",\"return_code\":\"0\",\"return_msg\":\"操作成功！\"}";
		str = str.replaceAll("\\\\", "");
		if(str.contains("biz_content\":\"[]\"")) {
			str = str.replace("\"[", "[").replace("]\"", "]");
		}else {
			str = str.replace("\"[{", "[{").replace("}]\"", "}]");
		}
		ETCQueryResponseDoc respDto = new ETCQueryResponseDoc();
		respDto = ParserFactory.getParser(ParserEnum.JSON, respDto.getClass()).parse(str);
		if(respDto.getBiz_content() != null && respDto.getBiz_content().size() > 0) {
			System.out.println(respDto.getBiz_content().get(0).getVc_id_code());
		}
	}
}
