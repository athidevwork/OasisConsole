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
public class EnvConfigType {
	List<OasisConfigType> oc;

	public EnvConfigType () {}
	
	public List<OasisConfigType> getOc() {
		return oc;
	}

	public void setOc(List<OasisConfigType> oc) {
		this.oc = oc;
	}

	public void addConfig (OasisConfigType oc) {
		this.oc.add(oc);
	}
	
	@Override
	public String toString() {
		return "EnvConfigType [oc=" + oc + "]";
	}	
}
