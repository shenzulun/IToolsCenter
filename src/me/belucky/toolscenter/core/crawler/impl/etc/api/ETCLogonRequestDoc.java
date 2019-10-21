/**
 * File Name: ETCRequestDTO.java
 * Date: 2019-10-15 14:53:13
 */
package me.belucky.toolscenter.core.crawler.impl.etc.api;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Description: ETC-登录请求
 * @author shenzulun
 * @date 2019-10-15
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class ETCLogonRequestDoc implements java.io.Serializable{
	private static final long serialVersionUID = -4247933822707729583L;
	
	private String method;
	
	private ETCLogonRequestBody biz_content;
	
	@JsonSerialize(include=Inclusion.NON_EMPTY)
	private String ticket;
	
	public ETCLogonRequestDoc() {}
	
	public ETCLogonRequestDoc(String method, ETCLogonRequestBody biz_content, String ticket) {
		super();
		this.method = method;
		this.biz_content = biz_content;
		this.ticket = ticket;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public ETCLogonRequestBody getBiz_content() {
		return biz_content;
	}

	public void setBiz_content(ETCLogonRequestBody biz_content) {
		this.biz_content = biz_content;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	
}
