/**
 * 
 */
package com.delphi.rest.service;

import org.springframework.stereotype.Service;

import com.delphi.rest.entity.OasisConfig;

/**
 * @author Athi
 *
 */
@Service("OasisConsoleService")
public interface OasisConsoleService {
	public OasisConfig getConfig();
}
