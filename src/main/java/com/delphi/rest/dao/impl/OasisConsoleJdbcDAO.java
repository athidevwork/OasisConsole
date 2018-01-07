/**
 * 
 */
package com.delphi.rest.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.delphi.rest.dao.OasisConsoleDAO;
import com.delphi.rest.entity.OasisConfig;
import com.delphi.rest.entity.SystemParameterUtil;

/**
 * @author Athi
 *
 */
public class OasisConsoleJdbcDAO implements OasisConsoleDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
	static Logger LOGGER = LoggerFactory.getLogger(OasisConsoleJdbcDAO.class);
   
    public OasisConfig getConfig() {
    	LOGGER.debug("In Dao impl");
    	/*log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
    	
        log.info("Querying for customer records where first_name = 'Josh':");
        jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" },
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        ).forEach(customer -> log.info(customer.toString()));*/
        
        String stmt = "select s.sysparm_code code, s.sysparm_value value, s.sysparm_description description\n"+       
        				"from system_parameter_util s \n" +
        				"where s.sysparm_code like 'ODS%' \n" +
        				"order by s.sysparm_code";
        List<SystemParameterUtil> list = jdbcTemplate.query(stmt, 
        					(rs, rowNum) -> new SystemParameterUtil(rs.getString("code"), rs.getString("value"), rs.getString("description")));
        
        list.forEach(System.out::println);
        OasisConfig config = new OasisConfig();
        config.setSpu(list);
        
        return config;
    }
}
