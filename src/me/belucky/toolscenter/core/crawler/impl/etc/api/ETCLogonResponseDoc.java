/**
 * File Name: ETCResponseDTO.java
 * Date: 2019-10-15 14:54:45
 */
package me.belucky.toolscenter.core.crawler.impl.etc.api;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Description: ETC-登录返回
 * @author shenzulun
 * @date 2019-10-15
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class ETCLogonResponseDoc implements java.io.Serializable{
	private static final long serialVersionUID = 92239906909079027L;
	
	private String method;
	
	private ETCLogonResponseBody biz_content;
	
	private String return_code;
	
	private String return_msg;
	
	public ETCLogonResponseDoc() {}
	
	public ETCLogonResponseDoc(String method, ETCLogonResponseBody biz_content, String return_code, String return_msg) {
		super();
		this.method = method;
		this.biz_content = biz_content;
		this.return_code = return_code;
		this.return_msg = return_msg;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public ETCLogonResponseBody getBiz_content() {
		return biz_content;
	}

	public void setBiz_content(ETCLogonResponseBody biz_content) {
		this.biz_content = biz_content;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

}
