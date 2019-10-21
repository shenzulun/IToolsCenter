/**
 * File Name: ETCQueryRequestDTO.java
 * Date: 2019-10-15 15:24:48
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
public class ETCQueryRequestBody implements java.io.Serializable{
	private static final long serialVersionUID = 23477260902331509L;

	private String vehicle_code;
	
	private String vehicle_color;
		
	public ETCQueryRequestBody() {}

	public ETCQueryRequestBody(String vehicle_code, String vehicle_color) {
		super();
		this.vehicle_code = vehicle_code;
		this.vehicle_color = vehicle_color;
	}

	public String getVehicle_code() {
		return vehicle_code;
	}

	public void setVehicle_code(String vehicle_code) {
		this.vehicle_code = vehicle_code;
	}

	public String getVehicle_color() {
		return vehicle_color;
	}

	public void setVehicle_color(String vehicle_color) {
		this.vehicle_color = vehicle_color;
	}	
}
