/**
 * 
 */
package com.delphi.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.delphi.rest.config.SpringApplicationContext;
import com.delphi.rest.entity.ODSConfig;
import com.delphi.rest.entity.OasisConfig;
import com.delphi.rest.entity.SystemParameterUtil;
import com.delphi.rest.service.OasisConsoleService;
import com.delphi.rest.service.impl.OasisConsoleServiceImpl;

/**
 * @author Athi
 *
 */
@Path("/oasisconsole")
public class OasisConsoleResource {
	static Logger LOGGER = LoggerFactory.getLogger(OasisConsoleResource.class);
	
	/*@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		LOGGER.debug("Setting app context");
		this.applicationContext = applicationContext;
	}*/
	
	@GET
	@Path("/home")
	public String sayHello() {
		return "Hello from Oasis Console";
	}
	
  @GET
  @Path("/{name}")
  public String sayHello(@PathParam("name") String name) {
    String output = "Hi from Jersey REST Service: " + name;
    return output;
  }
	  
  @GET
  @Path("/config")
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.TEXT_HTML})
  public Response getConfig() {
	  OasisConsoleService service = (OasisConsoleService) SpringApplicationContext.getBean("OasisConsoleServiceImpl");
	  LOGGER.debug("service = " + service);
	  //OasisConsoleService service = (OasisConsoleService) applicationContext.getBean(OasisConsoleServiceImpl.class);
	  //LOGGER.debug("service = " + service);
	  OasisConfig config = service.getConfig();
	  LOGGER.debug("config = " + config);
	  return Response.ok().entity(config).build();
  }
  
  @GET
  @Path("/config/html")
  @Produces({MediaType.TEXT_HTML})
  public Response getConfigHtml() {
	  OasisConsoleService service = (OasisConsoleService) SpringApplicationContext.getBean("OasisConsoleServiceImpl");
	  LOGGER.debug("service = " + service);
	  OasisConfig config = service.getConfig();
	  LOGGER.debug("config = " + config);
	  String html="<html><header><title>Oasis Config</title></header>"
	  				+ "<body><h1>Oasis DB Configuration</h1><hr><br>";
	  html += "<h2>ODS</h2>";
	  html += "<table border=4>";
	  html += "<tr><th>Code</th><th>Value</th><th>Description</th></tr><tr>";
	  for (SystemParameterUtil spu : config.getOds().getSystemParameterUtil()) {
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
	  html += "<h2>OS</h2>";
	  html += "<table border=4>";
	  html += "<tr><th>Code</th><th>Value</th><th>Description</th></tr><tr>";
	  for (SystemParameterUtil spu : config.getOs().getSystemParameterUtil()) {
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
	  html += "</body></html>";
	  return Response.ok().entity(html).build();
  }
}

