/**
 * 
 */
package com.delphi.rest.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Athi
 *
 */
@XmlRootElement
public class EnvConfigType {
	List<OasisConfigType> oasisConfig = new ArrayList<OasisConfigType>();

	public EnvConfigType () {}
	
	public List<OasisConfigType> getOasisConfig() {
		return oasisConfig;
	}

	public void setOasisConfig(List<OasisConfigType> oasisConfig) {
		this.oasisConfig = oasisConfig;
	}
	
	public void addConfig (OasisConfigType oc) {
		this.oasisConfig.add(oc);
	}
	
	@Override
	public String toString() {
		return "EnvConfigType [oasisConfig=" + oasisConfig + "]";
	}	
}
