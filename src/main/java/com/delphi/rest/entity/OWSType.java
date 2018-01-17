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
public class OWSType {
	List<SystemParameterUtilType> systemParameterUtil;

	public List<SystemParameterUtilType> getSystemParameterUtil() {
		return systemParameterUtil;
	}

	public void setSystemParameterUtil(List<SystemParameterUtilType> systemParameterUtil) {
		this.systemParameterUtil = systemParameterUtil;
	}

	@Override
	public String toString() {
		return "OWSType [spu=" + systemParameterUtil + "]";
	}
}
