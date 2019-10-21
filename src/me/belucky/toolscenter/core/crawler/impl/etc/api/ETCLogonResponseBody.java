/**
 * File Name: ETCLogonResponseDTO.java
 * Date: 2019-10-15 15:19:14
 */
package me.belucky.toolscenter.core.crawler.impl.etc.api;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Description: ETC登录请求返回报文体
 * @author shenzulun
 * @date 2019-10-15
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class ETCLogonResponseBody implements java.io.Serializable{
	private static final long serialVersionUID = 92239906909079027L;

	private String agency;
	
	private String branch_name;
	
	private String branch_no;
	
	private String op_code;
	
	private String op_name;
	
	private String rights;
	
	private String ticket;
	
	public ETCLogonResponseBody() {}

	public ETCLogonResponseBody(String agency, String branch_name, String branch_no, String op_code, String op_name,
			String rights, String ticket) {
		super();
		this.agency = agency;
		this.branch_name = branch_name;
		this.branch_no = branch_no;
		this.op_code = op_code;
		this.op_name = op_name;
		this.rights = rights;
		this.ticket = ticket;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getBranch_no() {
		return branch_no;
	}

	public void setBranch_no(String branch_no) {
		this.branch_no = branch_no;
	}

	public String getOp_code() {
		return op_code;
	}

	public void setOp_code(String op_code) {
		this.op_code = op_code;
	}

	public String getOp_name() {
		return op_name;
	}

	public void setOp_name(String op_name) {
		this.op_name = op_name;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	
}
