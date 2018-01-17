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
import com.delphi.rest.entity.DirType;
import com.delphi.rest.entity.ODSType;
import com.delphi.rest.entity.OSType;
import com.delphi.rest.entity.OWSType;
import com.delphi.rest.entity.OasisConfigType;
import com.delphi.rest.entity.SystemParameterUtilType;
import com.delphi.rest.util.OasisConsoleUtil;

/**
 * @author Athi
 *
 */
@Component("OasisConsoleJdbcDAO")
public class OasisConsoleJdbcDAO implements OasisConsoleDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
	static Logger LOGGER = LoggerFactory.getLogger(OasisConsoleJdbcDAO.class);
	
	static String SYS_PARAM_UTIL_STMT = "select s.sysparm_code code, s.sysparm_value value, s.sysparm_description description\n"+       
										"from system_parameter_util s \n" +
										"where s.sysparm_code like ? \n" +
										"order by s.sysparm_code";
   
    /*public OasisConfigType getConfig() {  
        OasisConfigType config = new OasisConfigType();
              
        List<SystemParameterUtilType> list = jdbcTemplate.query(SYS_PARAM_UTIL_STMT, new String[] { "ODS%" }, 
        								(rs, rowNum) -> new SystemParameterUtilType(rs.getString("code"), rs.getString("value"), rs.getString("description")));
        list.forEach(System.out::println);
        
        ODSType ods = new ODSType();
        ods.setSystemParameterUtil(list);
        
        list = jdbcTemplate.query(SYS_PARAM_UTIL_STMT, new Object[] { "OS%" }, 
				(rs, rowNum) -> new SystemParameterUtilType(rs.getString("code"), rs.getString("value"), rs.getString("description")));
        
		list.forEach(System.out::println);
        OSType os = new OSType();
        os.setSystemParameterUtil(list);
  
        config.setOds(ods);
        config.setOs(os);
        
        return config;
    }*/

	@Override
	public OasisConfigType getConfig(String env) {
		OasisConsoleUtil.setPropsForEnv(env);
		
		LOGGER.debug("*******ODS***********");
		//ODS parameters
        List<SystemParameterUtilType> list = jdbcTemplate.query(SYS_PARAM_UTIL_STMT, new String[] { "ODS%" }, 
				(rs, rowNum) -> new SystemParameterUtilType(rs.getString("code"), rs.getString("value"), rs.getString("description")));
		list.forEach(System.out::println);
		
		ODSType ods = new ODSType();
		ods.setSystemParameterUtil(list);
		
		LOGGER.debug("*******OS***********");
		//OS parameters
		list = jdbcTemplate.query(SYS_PARAM_UTIL_STMT, new Object[] { "OS%" }, 
				(rs, rowNum) -> new SystemParameterUtilType(rs.getString("code"), rs.getString("value"), rs.getString("description")));
		
		list.forEach(System.out::println);
		OSType os = new OSType();
		os.setSystemParameterUtil(list);
		
		//OWS parameters
		list = jdbcTemplate.query(SYS_PARAM_UTIL_STMT, new Object[] { "OWS%" }, 
				(rs, rowNum) -> new SystemParameterUtilType(rs.getString("code"), rs.getString("value"), rs.getString("description")));
		
		LOGGER.debug("*******OWS***********");
		list.forEach(System.out::println);
		OWSType ows = new OWSType();
		ows.setSystemParameterUtil(list);
		
		//DIR parameters
		list = jdbcTemplate.query(SYS_PARAM_UTIL_STMT, new Object[] { "DIR%" }, 
				(rs, rowNum) -> new SystemParameterUtilType(rs.getString("code"), rs.getString("value"), rs.getString("description")));
		
		LOGGER.debug("*******DIR***********");
		list.forEach(System.out::println);
		DirType dir = new DirType();
		dir.setSystemParameterUtil(list);		
		
		OasisConfigType config = new OasisConfigType();
		config.setEnv(env);
		config.setOds(ods);
		config.setOs(os);
		config.setOws(ows);
		config.setDir(dir);

		return config;
	}
}
