/**
 * 
 */
package com.delphi.rest.service;

import org.springframework.stereotype.Service;

import com.delphi.rest.entity.OasisConfigType;

/**
 * @author Athi
 *
 */
@Service("OasisConsoleService")
public interface OasisConsoleService {
	//public OasisConfigType getConfig();
	public OasisConfigType getConfig(String env);
}
