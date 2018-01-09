/**
 * 
 */
package com.delphi.rest.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.delphi.rest.dao.OasisConsoleDAO;
import com.delphi.rest.entity.ODSConfig;
import com.delphi.rest.entity.OSConfig;
import com.delphi.rest.entity.OasisConfig;
import com.delphi.rest.entity.SystemParameterUtil;

/**
 * @author Athi
 *
 */
@Component("OasisConsoleJdbcDAO")
public class OasisConsoleJdbcDAO implements OasisConsoleDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
	static Logger LOGGER = LoggerFactory.getLogger(OasisConsoleJdbcDAO.class);
   
    public OasisConfig getConfig() {
    	//LOGGER.debug("In Dao impl");
    	/*log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
    	
        log.info("Querying for customer records where first_name = 'Josh':");
        jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" },
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        ).forEach(customer -> log.info(customer.toString()));*/
  
        OasisConfig config = new OasisConfig();
        
        String stmt = "select s.sysparm_code code, s.sysparm_value value, s.sysparm_description description\n"+       
        				"from system_parameter_util s \n" +
        				"where s.sysparm_code like 'ODS%' \n" +
        				"order by s.sysparm_code";
        List<SystemParameterUtil> list = jdbcTemplate.query(stmt, 
        					(rs, rowNum) -> new SystemParameterUtil(rs.getString("code"), rs.getString("value"), rs.getString("description")));
        
        list.forEach(System.out::println);
        
        ODSConfig ods = new ODSConfig();
        ods.setSystemParameterUtil(list);
        
        String stmt1 = "select s.sysparm_code code, s.sysparm_value value, s.sysparm_description description\n"+       
				"from system_parameter_util s \n" +
				"where s.sysparm_code like 'OS%' \n" +
				"order by s.sysparm_code";
        list = jdbcTemplate.query(stmt1, 
					(rs, rowNum) -> new SystemParameterUtil(rs.getString("code"), rs.getString("value"), rs.getString("description")));

		list.forEach(System.out::println);
        OSConfig os = new OSConfig();
        os.setSystemParameterUtil(list);
  
        config.setOds(ods);
        config.setOs(os);
        
        return config;
    }
}
