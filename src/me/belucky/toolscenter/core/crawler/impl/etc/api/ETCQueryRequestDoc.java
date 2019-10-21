/**
 * File Name: ETCQueryRequestDoc.java
 * Date: 2019-10-16 08:42:25
 */
package me.belucky.toolscenter.core.crawler.impl.etc.api;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Description: 
 * @author shenzulun
 * @date 2019-10-16
 * @version 1.0
 */
public class ETCQueryRequestDoc implements java.io.Serializable{
	private static final long serialVersionUID = -4247933822707729583L;
	
	private String method;
	
	private ETCQueryRequestBody biz_content;
	
	@JsonSerialize(include=Inclusion.NON_EMPTY)
	private String ticket;
	
	public ETCQueryRequestDoc() {}
	
	public ETCQueryRequestDoc(String method, ETCQueryRequestBody biz_content, String ticket) {
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

	public ETCQueryRequestBody getBiz_content() {
		return biz_content;
	}

	public void setBiz_content(ETCQueryRequestBody biz_content) {
		this.biz_content = biz_content;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
}
