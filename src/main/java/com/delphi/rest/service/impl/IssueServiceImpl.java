/**
 * 
 */
package com.delphi.rest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.delphi.rest.config.SpringApplicationContext;
import com.delphi.rest.dao.impl.IssueJdbcDAO;
import com.delphi.rest.entity.policy.PolicyDataType;
import com.delphi.rest.service.IssueService;

/**
 * @author Athi
 *
 */
@Service("IssueServiceImpl")
public class IssueServiceImpl implements IssueService {
	static Logger LOGGER = LoggerFactory.getLogger(IssueServiceImpl.class);
	
	@Override
	public PolicyDataType getPolicyData(String env, String policy) {
		IssueJdbcDAO dao = (IssueJdbcDAO) SpringApplicationContext.getBean("IssueJdbcDAO");
		//LOGGER.debug("dao = " + dao);
		PolicyDataType config = dao.getPolicyData(env, policy);
		return config;
	}	
}
