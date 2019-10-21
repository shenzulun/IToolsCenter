/**
 * File Name: ETCLogonRequestDTO.java
 * Date: 2019-10-15 15:17:53
 */
package me.belucky.toolscenter.core.crawler.impl.etc.api;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Description: ETC登录请求报文体
 * @author shenzulun
 * @date 2019-10-15
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class ETCLogonRequestBody implements java.io.Serializable{
	private static final long serialVersionUID = -4247933822707729583L;

	private String op_code;
	
	private String op_pwd;
	
	public ETCLogonRequestBody() {}
	
	public ETCLogonRequestBody(String op_code, String op_pwd) {
		super();
		this.op_code = op_code;
		this.op_pwd = op_pwd;
	}

	public String getOp_code() {
		return op_code;
	}

	public void setOp_code(String op_code) {
		this.op_code = op_code;
	}

	public String getOp_pwd() {
		return op_pwd;
	}

	public void setOp_pwd(String op_pwd) {
		this.op_pwd = op_pwd;
	}
	
	
}
