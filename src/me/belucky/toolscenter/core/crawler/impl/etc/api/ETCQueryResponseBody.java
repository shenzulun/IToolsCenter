/**
 * File Name: ETCQueryResponseDTO.java
 * Date: 2019-10-15 15:26:10
 */
package me.belucky.toolscenter.core.crawler.impl.etc.api;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Description: 
 * @author shenzulun
 * @date 2019-10-15
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class ETCQueryResponseBody implements java.io.Serializable{
	private static final long serialVersionUID = -6665413612051196015L;
	
	private String is_issued;
	
	private String is_signed;
	
	private String n_seats;
	
	private String n_weight;
	
	private String vc_address;
	
	private String vc_engine;
	
	private String vc_id_code;
	
	private String vc_owner_name;
	
	private String vc_type;
	
	private String vc_use_type;
	
	private String vc_vehicle_code;
	
	private String vc_vin;
	
	public ETCQueryResponseBody() {}
	
	public ETCQueryResponseBody(String is_issued, String is_signed, String n_seats, String n_weight, String vc_address,
			String vc_engine, String vc_id_code, String vc_owner_name, String vc_type, String vc_use_type,
			String vc_vehicle_code, String vc_vin) {
		super();
		this.is_issued = is_issued;
		this.is_signed = is_signed;
		this.n_seats = n_seats;
		this.n_weight = n_weight;
		this.vc_address = vc_address;
		this.vc_engine = vc_engine;
		this.vc_id_code = vc_id_code;
		this.vc_owner_name = vc_owner_name;
		this.vc_type = vc_type;
		this.vc_use_type = vc_use_type;
		this.vc_vehicle_code = vc_vehicle_code;
		this.vc_vin = vc_vin;
	}

	public String getIs_issued() {
		return is_issued;
	}

	public void setIs_issued(String is_issued) {
		this.is_issued = is_issued;
	}

	public String getIs_signed() {
		return is_signed;
	}

	public void setIs_signed(String is_signed) {
		this.is_signed = is_signed;
	}

	public String getN_seats() {
		return n_seats;
	}

	public void setN_seats(String n_seats) {
		this.n_seats = n_seats;
	}

	public String getN_weight() {
		return n_weight;
	}

	public void setN_weight(String n_weight) {
		this.n_weight = n_weight;
	}

	public String getVc_address() {
		return vc_address;
	}

	public void setVc_address(String vc_address) {
		this.vc_address = vc_address;
	}

	public String getVc_engine() {
		return vc_engine;
	}

	public void setVc_engine(String vc_engine) {
		this.vc_engine = vc_engine;
	}

	public String getVc_id_code() {
		return vc_id_code;
	}

	public void setVc_id_code(String vc_id_code) {
		this.vc_id_code = vc_id_code;
	}

	public String getVc_owner_name() {
		return vc_owner_name;
	}

	public void setVc_owner_name(String vc_owner_name) {
		this.vc_owner_name = vc_owner_name;
	}

	public String getVc_type() {
		return vc_type;
	}

	public void setVc_type(String vc_type) {
		this.vc_type = vc_type;
	}

	public String getVc_use_type() {
		return vc_use_type;
	}

	public void setVc_use_type(String vc_use_type) {
		this.vc_use_type = vc_use_type;
	}

	public String getVc_vehicle_code() {
		return vc_vehicle_code;
	}

	public void setVc_vehicle_code(String vc_vehicle_code) {
		this.vc_vehicle_code = vc_vehicle_code;
	}

	public String getVc_vin() {
		return vc_vin;
	}

	public void setVc_vin(String vc_vin) {
		this.vc_vin = vc_vin;
	}
	
}
