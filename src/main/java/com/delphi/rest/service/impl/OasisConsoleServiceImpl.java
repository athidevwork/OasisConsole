/**
 * 
 */
package com.delphi.rest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.delphi.rest.dao.OasisConsoleDAO;
import com.delphi.rest.entity.OasisConfig;
import com.delphi.rest.resource.OasisConsoleResource;
import com.delphi.rest.service.OasisConsoleService;

/**
 * @author Athi
 *
 */
public class OasisConsoleServiceImpl implements OasisConsoleService {
	@Autowired
	private ApplicationContext applicationContext;
	  
	static Logger LOGGER = LoggerFactory.getLogger(OasisConsoleServiceImpl.class);
	
	@Override
	public OasisConfig getConfig() {
		OasisConsoleDAO dao = (OasisConsoleDAO) applicationContext.getBean(OasisConsoleDAO.class);
		LOGGER.debug("dao = " + dao);
		OasisConfig config = dao.getConfig();
		return config;
	}

}
