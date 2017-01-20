package com.bhargava.validationresponse;

import java.util.HashMap;
import java.util.Map;

/**
 * ValidationResponse file that is transformed to a json between the Controller and the JS.
 * @author Bhargava
 *
 */
public class ValidationResponse {
	private String status;
	private Map<String, String> errors;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public void addError(String field, String defaultMessage) {
		if (this.errors == null) {
			this.errors = new HashMap<String, String>();
		}
		StringBuffer earlier = new StringBuffer();
		if (this.errors.get(field) != null) {
			earlier.append(this.errors.get(field));
			earlier.append("<br/>");
		}
		earlier.append(defaultMessage);
		
		this.errors.put(field, earlier.toString());
	}

}