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
import com.delphi.rest.entity.policy.PolicyDataType;
import com.delphi.rest.entity.policy.PolicyTermType;
import com.delphi.rest.entity.policy.PolicyTransactionType;
import com.delphi.rest.entity.policy.PolicyType;

/**
 * @author Athi
 *
 */
@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class OasisConsoleUtil {
	static Logger LOGGER = LoggerFactory.getLogger(OasisConsoleUtil.class);

	/*public static void setPropsForLinks (String env) {
		workcenter.mag20171se=http://ny2-wl12211dti1.ad.dti:8181/wl12211dti1/mag/mag20171se/CS/login.jsp
		ecis.mag20171se=http://ny2-wl12211dti1.ad.dti:8181/wl12211dti1/mag/mag20171se/CIS/login.jsp
		eadmin.mag20171se=http://ny2-wl12211dti1.ad.dti:8181/wl12211dti1/mag/mag20171se/eAdmin/CustWebWB/core/login.jsp
		ods.mag20171se=http://ny2-os171bi417a.ad.dti/ODSConsole/Configuration/ConfigServerDetails.aspx?action=edit&odsID=MAG20171SE
		edi.mag20171se=http://ny2-wl12211dti1.ad.dti:8181/wl12211dti1/mag/mag20171se/eCS/wsCS/EdiImportService

				
		String workcenter = (String) SpringApplicationContext.getProperty("workcenter."+env);
		String eCis = (String) SpringApplicationContext.getProperty("ecis."+env);
		String eAdmin = (String) SpringApplicationContext.getProperty("eadmin."+env);
		String ods = (String) SpringApplicationContext.getProperty("ods."+env);
		String edi = (String) SpringApplicationContext.getProperty("edi."+env);
		LOGGER.debug("Env workcenter."+env+" : " + workcenter);
		LOGGER.debug("Env ecis."+env+" : " + eCis);
		LOGGER.debug("Env eadmin."+env+" : " + eAdmin);
		LOGGER.debug("Env ods."+env+" : " + ods);
		LOGGER.debug("Env edi."+env+" : " + edi);
	
		/*if (workcenter.isEmpty() || eCis.isEmpty() || eAdmin.isEmpty() || ods.isEmpty() || edi.isEmpty()) 
			LOGGER.debug("Environment setting for env " + env + " not found. Using default setting for datasource");
		else {
			DriverManagerDataSource datasource = (DriverManagerDataSource) SpringApplicationContext.getBean("dataSource");
			datasource.setUrl(workcenter);
			datasource.setUsername(eCis);
			datasource.setPassword(eAdmin);
		}
		
		LOGGER.debug("Datasource set for parameter spring.datasource.url : " + workcenter);
		LOGGER.debug("Datasource set for parameter spring.datasource.username : " + eCis);
		LOGGER.debug("Datasource set for parameter spring.datasource.password : " + eAdmin);
	}*/
	
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

	public static String translatePolicy2Html(PolicyDataType policyData, String env) {
		String html = "<html><header><title>Policy Data for " + env + "</title></header>"
				+ "<body><h1>Oasis DB Configuration for " + env + "</h1><hr><br>";
		html = translatePolicyBlock2Html(policyData, html);
		html += "</body></html>";
		return html;
	}
	
	private static String translatePolicyBlock2Html(PolicyDataType policyData, String html) {
		html += "<h2>Policy</h2>";
		html += "<table border=4>";
		html += "<tr><th>POLICY_PK</th><th>POLICY_NO</th><th>POLICY_CYCLE_CODE</th>"
				+ "<th>POLICY_TYPE_CODE</th><th>POL_CURR_STATUS_CODE</th>"
				+ "<th>POL_CURR_REASON_CODE</th><th>PROCESS_STATUS_CODE</th>"
				+ "<th>LEGACY_POLICY_NO</th><th>INCEPTION_DATE</th><th>SID</th>"
				+ "<th>SERIAL_NUMBER</th><th>LOGON_TIME</th><th>WIP_B</th><th>LOCK_TIME</th>"
				+ "<th>LEGACY_ISSUE_COMPANY_FK</th><th>FORMATTED_POL_NO</th></tr><tr>";
		for (PolicyType policy : policyData.getPolicy()) {
			html += "<tr>";
			html += "<td>" + policy.getPOLICY_PK() + "</td>";
			html += "<td>" + policy.getPOLICY_NO() + "</td>";
			html += "<td>" + policy.getPOLICY_CYCLE_CODE() + "</td>";
			html += "<td>" + policy.getPOLICY_TYPE_CODE() + "</td>";
			html += "<td>" + policy.getPOL_CURR_STATUS_CODE() + "</td>";
			html += "<td>" + policy.getPOL_CURR_REASON_CODE() + "</td>";
			html += "<td>" + policy.getPROCESS_STATUS_CODE() + "</td>";
			html += "<td>" + policy.getLEGACY_POLICY_NO() + "</td>";
			html += "<td>" + policy.getINCEPTION_DATE() + "</td>";
			html += "<td>" + policy.getSID() + "</td>";
			html += "<td>" + policy.getSERIAL_NUMBER() + "</td>";
			html += "<td>" + policy.getLOGON_TIME() + "</td>";
			html += "<td>" + policy.getWIP_B() + "</td>";
			html += "<td>" + policy.getLOCK_TIME() + "</td>";
			html += "<td>" + policy.getLEGACY_ISSUE_COMPANY_FK() + "</td>";
			html += "<td>" + policy.getFORMATTED_POL_NO() + "</td>";
			html += "</tr>";
		}
		html += "</table>";
		
		html += "<h2>Policy Terms</h2>";
		html += "<table border=4>";
		html += "<tr><th>term_pk</th><th>term_base_fk</th><th>base_record</th>"
				+ "<th>pol_rel_stat_type_code</th><th>term</th><th>record_mode_code</th>"
				+ "<th>official</th><th>trans</th><th>close</th><th>effec_from</th>"
				+ "<th>effec_to</th><th>acct_from</th><th>acct_to</th><th>last_trans</th>"
				+ "<th>issue_state_code</th><th>issue_company_entity_fk</th><th>process_location_code</th>"
				+ "<th>short_term_b</th><th>renewal_cycle_code</th>"
				+ "<th>renewal_INDICATOR_code</th><th>peer_groups_code</th></tr><tr>";
		for (PolicyTermType policyTerm : policyData.getPolicyTerm()) {
			html += "<tr>";
			html += "<td>" + policyTerm.getTerm_pk() + "</td>";
			html += "<td>" + policyTerm.getTerm_base_fk() + "</td>";
			html += "<td>" + policyTerm.getBase_record() + "</td>";
			html += "<td>" + policyTerm.getPol_rel_stat_type_code() + "</td>";
			html += "<td>" + policyTerm.getTerm() + "</td>";
			html += "<td>" + policyTerm.getRecord_mode_code() + "</td>";
			html += "<td>" + policyTerm.getOfficial() + "</td>";
			html += "<td>" + policyTerm.getTrans() + "</td>";
			html += "<td>" + policyTerm.getClose() + "</td>";
			html += "<td>" + policyTerm.getEffec_from() + "</td>";
			html += "<td>" + policyTerm.getEffec_to() + "</td>";
			html += "<td>" + policyTerm.getAcct_from() + "</td>";
			html += "<td>" + policyTerm.getAcct_to() + "</td>";
			html += "<td>" + policyTerm.getLast_trans() + "</td>";
			html += "<td>" + policyTerm.getIssue_state_code() + "</td>";
			html += "<td>" + policyTerm.getIssue_company_entity_fk() + "</td>";
			html += "<td>" + policyTerm.getProcess_location_code() + "</td>";
			html += "<td>" + policyTerm.getShort_term_b() + "</td>";
			html += "<td>" + policyTerm.getRenewal_cycle_code() + "</td>";
			html += "<td>" + policyTerm.getRenewal_INDICATOR_code() + "</td>";
			html += "<td>" + policyTerm.getPeer_groups_code() + "</td>";
			html += "</tr>";
		}
		html += "</table>";
	
		html += "<h2>Policy Transaction</h2>";
		html += "<table border=4>";
		html += "<tr><th>TRANSACTION_LOG_PK</th><th>POLICY_FK</th><th>TRANSACTION_TYPE_CODE</th>"
				+ "<th>TRANSACTION_CODE</th><th>ENDORSEMENT_QUOTE_ID</th>"
				+ "<th>EFFECTIVE_FROM_DATE</th><th>ACCOUNTING_DATE</th>"
				+ "<th>ENDORSEMENT_CODE</th><th>COMMENTS</th></tr><tr>";
		for (PolicyTransactionType policyTrans : policyData.getPolicyTransaction()) {
			html += "<tr>";
			html += "<td>" + policyTrans.getTRANSACTION_LOG_PK() + "</td>";
			html += "<td>" + policyTrans.getPOLICY_FK() + "</td>";
			html += "<td>" + policyTrans.getTRANSACTION_TYPE_CODE() + "</td>";
			html += "<td>" + policyTrans.getTRANSACTION_CODE() + "</td>";
			html += "<td>" + policyTrans.getENDORSEMENT_QUOTE_ID() + "</td>";
			html += "<td>" + policyTrans.getEFFECTIVE_FROM_DATE() + "</td>";
			html += "<td>" + policyTrans.getACCOUNTING_DATE() + "</td>";
			html += "<td>" + policyTrans.getENDORSEMENT_CODE() + "</td>";
			html += "<td>" + policyTrans.getCOMMENTS() + "</td>";
			html += "</tr>";
		}
		html += "</table>";
		
		return html;
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
	
	public static String getLinkFor(String env, String product) {
		Properties props = OasisConsoleUtil.getAllProperties();
		
        for(Entry<Object, Object> e : props.entrySet()) {
        	String key = (String) e.getKey();
        	String value = (String) e.getValue();
        	if (key.contains(env + ".links." + product)) {
        		LOGGER.debug (" Found property for " + product + "=" + value);
        		return value;
        	}
        }
        LOGGER.debug (" Did not find property for " + product);
		return null;
	}
	
	public static Map<String, String> getLinksForEnv(String env) {
		Map<String, String> linksMap = new HashMap<String, String>();
		
		Properties props = OasisConsoleUtil.getAllProperties();
	
        for(Entry<Object, Object> e : props.entrySet()) {
        	String key = (String) e.getKey();
        	String value = (String) e.getValue();
        	//LOGGER.debug("Before check : " + key);
        	if (key.contains(env + ".links")) {
        		linksMap.put(key, value);
        	}
        }
		return linksMap;
	}
	
	public static Map<String, String> getAllLinks() {
		Map<String, String> linksMap = new HashMap<String, String>();
		
		Properties props = OasisConsoleUtil.getAllProperties();
	
        for(Entry<Object, Object> e : props.entrySet()) {
        	String key = (String) e.getKey();
        	String value = (String) e.getValue();
        	//LOGGER.debug("Before check : " + key);
        	if (key.contains(".links")) {
        		linksMap.put(key, value);
        	}
        }
        return linksMap;
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
