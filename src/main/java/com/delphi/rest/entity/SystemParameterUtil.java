/**
 * 
 */
package com.delphi.rest.entity;

/**
 * @author Athi
 *
 */
public class SystemParameterUtil {
	String code;
	String value;
	String description;
	
	public SystemParameterUtil(String code, String value, String description) {
		super();
		this.code = code;
		this.value = value;
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "SystemParameterUtil [code=" + code + ", value=" + value + ", description=" + description + "]";
	}
}
