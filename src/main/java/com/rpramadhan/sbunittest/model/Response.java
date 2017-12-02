package com.rpramadhan.sbunittest.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Response implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6143474484193228992L;

	@JsonProperty("responseCode")
	private String responseCode;
	
	@JsonProperty("responseDesc")
	private String responseDesc;
	
	@JsonProperty("result")
	private Object result;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDesc() {
		return responseDesc;
	}

	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
