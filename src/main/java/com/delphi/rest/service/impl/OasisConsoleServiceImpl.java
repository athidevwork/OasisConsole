/**
 * 
 */
package com.delphi.rest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.delphi.rest.config.SpringApplicationContext;
import com.delphi.rest.dao.OasisConsoleDAO;
import com.delphi.rest.entity.OasisConfig;
import com.delphi.rest.service.OasisConsoleService;

/**
 * @author Athi
 *
 */
@Service("OasisConsoleServiceImpl")
public class OasisConsoleServiceImpl implements OasisConsoleService {	  
	static Logger LOGGER = LoggerFactory.getLogger(OasisConsoleServiceImpl.class);
	
	@Override
	public OasisConfig getConfig() {
		OasisConsoleDAO dao = (OasisConsoleDAO) SpringApplicationContext.getBean("OasisConsoleJdbcDAO");
		LOGGER.debug("dao = " + dao);
		//OasisConsoleDAO dao = (OasisConsoleDAO) applicationContext.getBean(OasisConsoleDAO.class);
		LOGGER.debug("dao = " + dao);
		OasisConfig config = dao.getConfig();
		return config;
	}

}
