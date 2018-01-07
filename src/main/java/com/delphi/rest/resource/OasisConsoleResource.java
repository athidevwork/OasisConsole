/**
 * 
 */
package com.delphi.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.delphi.rest.config.SpringApplicationContext;
import com.delphi.rest.entity.OasisConfig;
import com.delphi.rest.service.OasisConsoleService;

/**
 * @author Athi
 *
 */
@Path("/oasisconsole")
public class OasisConsoleResource implements ApplicationContextAware {
	static Logger LOGGER = LoggerFactory.getLogger(OasisConsoleResource.class);
	
	/*@Autowired*/
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		LOGGER.debug("Setting app context");
		this.applicationContext = applicationContext;
	}
	
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
  public Response getConfig() {
	  LOGGER.debug("appContext = " + applicationContext);
	  //OasisConsoleService service = (OasisConsoleService) SpringApplicationContext.getBean("OasisConsoleService");
	  OasisConsoleService service = (OasisConsoleService) applicationContext.getBean(OasisConsoleService.class);
	  LOGGER.debug("service = " + service);
	  OasisConfig config = service.getConfig();
	  LOGGER.debug("config = " + config);
	  return Response.ok().entity(config).build();
  }
}

