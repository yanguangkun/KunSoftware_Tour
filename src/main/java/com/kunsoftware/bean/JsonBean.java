package com.kunsoftware.bean;

import java.util.HashMap;

@SuppressWarnings({ "serial", "rawtypes","unchecked" })
public class JsonBean extends HashMap {

	private String success = "1";
	private String message;
	
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		put("success", success);
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		put("message", message);
		this.message = message;
	}
	
	public void put(String key,Object value) {
		super.put(key, value);
	}
}
