/**
 * 
 */
package com.delphi.rest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.delphi.rest.config.SpringApplicationContext;
import com.delphi.rest.entity.OasisConfigType;
import com.delphi.rest.entity.SystemParameterUtilType;

/**
 * @author Athi
 *
 */
@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class OasisConsoleUtil {
	static Logger LOGGER = LoggerFactory.getLogger(OasisConsoleUtil.class);
	
	public static void setPropsForEnv (String env) {
		//replaceSpringProperties();
		
		String url = (String) SpringApplicationContext.getProperty("spring.datasource.url."+env);
		String username = (String) SpringApplicationContext.getProperty("spring.datasource.username."+env);
		String password = (String) SpringApplicationContext.getProperty("spring.datasource.password."+env);
		LOGGER.debug("Env spring.datasource.url."+env+" : " + url);
		LOGGER.debug("Env spring.datasource.username."+env+" : " + username);
		LOGGER.debug("Env spring.datasource.password."+env+" : " + password);
	
		if (url.isEmpty() || username.isEmpty() || password.isEmpty()) 
			LOGGER.debug("Environment setting for env " + env + " not found. Using default setting for datasource");
		else {
			DriverManagerDataSource datasource = (DriverManagerDataSource) SpringApplicationContext.getBean("dataSource");
			datasource.setUrl(url);
			datasource.setUsername(username);
			datasource.setPassword(password);
		}
		
		LOGGER.debug("Datasource set for parameter spring.datasource.url : " + url);
		LOGGER.debug("Datasource set for parameter spring.datasource.username : " + username);
		LOGGER.debug("Datasource set for parameter spring.datasource.password : " + password);
	}

	public static String translateConfig2Html(OasisConfigType config, String env) {
		String html="<html><header><title>Oasis Config for " + env + "</title></header>"
		  				+ "<body><h1>Oasis DB Configuration for " + env + "</h1><hr><br>";
		  html = translateConfigBlock2Html(config, html);
		  html += "</body></html>";
		return html;
	}

	public static String translateConfigBlock2Html(OasisConfigType config, String html) {
		html = getSysParmUtilHtml(config, html, "ODS");
		html = getSysParmUtilHtml(config, html, "OS");
		html = getSysParmUtilHtml(config, html, "OWS");
		html = getSysParmUtilHtml(config, html, "DIR");
		return html;
	}

	private static String getSysParmUtilHtml(OasisConfigType config, String html, String domain) {
		  List<SystemParameterUtilType> list = null;
		  switch (domain) {
		  case "ods":
		  case "ODS":
			  if (config.getOds() == null)
				  list = null;
			  else
				  list = config.getOds().getSystemParameterUtil();
			  break;
		  case "os":
		  case "OS":
			  if (config.getOs() == null)
				  list = null;
			  else
				  list = config.getOs().getSystemParameterUtil();
			  break;	
		  case "ows":
		  case "OWS":
			  if (config.getOws() == null)
				  list = null;
			  else
				  list = config.getOws().getSystemParameterUtil();
			  break;
		  case "dir":
		  case "DIR":
			  if (config.getDir() == null)
				  list = null;
			  else
				  list = config.getDir().getSystemParameterUtil();
			  break;			  
		  default:
			  list = null;
		  }
		  if (list != null) {
			  html += "</table>";
				  html += "<h2>"+domain+"</h2>";
				  html += "<table border=4>";
				  html += "<tr><th>Code</th><th>Value</th><th>Description</th></tr><tr>";
			  for (SystemParameterUtilType spu : list) {
				  html += "<tr><td>";
				  html += spu.getCode();
				  html += "</td>";
				  html += "<td>";
				  html += spu.getValue();
				  html += "</td>";
				  html += "<td>";
				  html += spu.getDescription();
				  html += "</td></tr>";
			  }
			  html += "</table>";
		  }
		return html;
	}
	
	public static Properties getAllProperties() {
		Properties props = new Properties();
		AbstractEnvironment springEnv = (AbstractEnvironment) SpringApplicationContext.getApplicationContext().getEnvironment();
		
		MutablePropertySources propSrcs = ((AbstractEnvironment) springEnv).getPropertySources();
		StreamSupport.stream(propSrcs.spliterator(), false)
		        .filter(ps -> ps instanceof EnumerablePropertySource)
		        .map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
		        .flatMap(Arrays::<String>stream)
		        .forEach(propName -> props.setProperty(propName, springEnv.getProperty(propName)));
		
		return props;
	}
	
	public static List<String> getEnvironments() {
		Properties envProps = new Properties();
		//Map<String, String> envMap = new HashMap<String, String>();
		List<String> envList = new ArrayList<String>();
		
		Properties props = OasisConsoleUtil.getAllProperties();
	
        for(Entry<Object, Object> e : props.entrySet()) {
        	String key = (String) e.getKey();
        	//LOGGER.debug("Before check : " + key);
        	if (key.contains("spring.datasource.url.")) {
        		envProps.setProperty(key, (String)e.getValue());
        		//LOGGER.debug(key);
        		//envList.add(key.substring(key.lastIndexOf(".")+1, key.length()));
        		String envName = key.substring(key.lastIndexOf(".")+1, key.length());
        		//envMap.put(envName, envName);
        		envList.add(envName);
        	}
        }
		
		LOGGER.debug("Environments List : " + envList);
		LOGGER.debug("Env Props List : " + envProps);
		return envList;
	}
	
	private void replaceSpringProperties() {
		FileInputStream in = null;
		try {
			in = new FileInputStream("application.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		FileOutputStream out = null;
		try {
			out = new FileOutputStream("application.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		props.setProperty("country", "america");
		try {
			props.store(out, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	}
}
