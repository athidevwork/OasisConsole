/**
 * 
 */
package com.delphi.rest.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Athi
 *
 */
@XmlRootElement
public class EnvSupportedType {
	List<String> env;

	public EnvSupportedType () {}
	
	public List<String> getEnv() {
		return env;
	}

	public void setEnv(List<String> envSupported) {
		this.env = envSupported;
	}

	@Override
	public String toString() {
		return "EnvSupportedType [envSupported=" + env + "]";
	}	
}
