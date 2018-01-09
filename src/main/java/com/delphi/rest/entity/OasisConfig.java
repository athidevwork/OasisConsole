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
public class OasisConfig {
	OSConfig os;
	ODSConfig ods;
	
	public OasisConfig() {
		super();
	}

	public OSConfig getOs() {
		return os;
	}

	public void setOs(OSConfig os) {
		this.os = os;
	}

	public ODSConfig getOds() {
		return ods;
	}

	public void setOds(ODSConfig ods) {
		this.ods = ods;
	}

	@Override
	public String toString() {
		return "OasisConfig [os=" + os + ", ods=" + ods + "]";
	}
}
