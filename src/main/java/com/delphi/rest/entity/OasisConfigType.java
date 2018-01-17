/**
 * 
 */
package com.delphi.rest.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Athi
 *
 */
@XmlRootElement
public class OasisConfigType {
	String env;
	DirType dir;
	OSType os;
	ODSType ods;
	OWSType ows;
	
	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public OasisConfigType() {
		super();
	}

	public DirType getDir() {
		return dir;
	}

	public void setDir(DirType dir) {
		this.dir = dir;
	}

	public OSType getOs() {
		return os;
	}

	public void setOs(OSType os) {
		this.os = os;
	}

	public ODSType getOds() {
		return ods;
	}

	public void setOds(ODSType ods) {
		this.ods = ods;
	}

	public OWSType getOws() {
		return ows;
	}

	public void setOws(OWSType ows) {
		this.ows = ows;
	}

	@Override
	public String toString() {
		return "OasisConfigType [env=" + env + ", dir=" + dir + ", os=" + os + ", ods=" + ods + ", ows=" + ows + "]";
	}
}
