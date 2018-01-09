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
public class OSConfig {
	List<SystemParameterUtil> systemParameterUtil;

	public List<SystemParameterUtil> getSystemParameterUtil() {
		return systemParameterUtil;
	}

	public void setSystemParameterUtil(List<SystemParameterUtil> systemParameterUtil) {
		this.systemParameterUtil = systemParameterUtil;
	}

	@Override
	public String toString() {
		return "OasisConfig [spu=" + systemParameterUtil + "]";
	}
}
