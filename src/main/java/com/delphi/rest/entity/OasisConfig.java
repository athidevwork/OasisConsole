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
	List<SystemParameterUtil> spu;

	public List<SystemParameterUtil> getSpu() {
		return spu;
	}

	public void setSpu(List<SystemParameterUtil> spu) {
		this.spu = spu;
	}

	@Override
	public String toString() {
		return "OasisConfig [spu=" + spu + "]";
	}
}
