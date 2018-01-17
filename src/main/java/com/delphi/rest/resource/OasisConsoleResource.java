/**
 * 
 */
package com.delphi.rest.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.delphi.rest.config.SpringApplicationContext;
import com.delphi.rest.entity.EnvConfigType;
import com.delphi.rest.entity.OasisConfigType;
import com.delphi.rest.service.OasisConsoleService;
import com.delphi.rest.util.OasisConsoleUtil;

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
  @Path("/envlist")
  public List<String> getEnv() {
	return OasisConsoleUtil.getEnvironments();
  }
	
  @GET
  @Path("/config/{env}")
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.TEXT_HTML})
  public Response getEnvConfig(@PathParam("env") String env) {
	  LOGGER.debug("Getting config data for env : " + env);
	  OasisConsoleService service = (OasisConsoleService) SpringApplicationContext.getBean("OasisConsoleServiceImpl");
	  OasisConfigType config = service.getConfig(env);
	  return Response.ok().entity(config).build();
  }

  @GET
  @Path("/htmlconfig/{env}")
  @Produces({MediaType.TEXT_HTML})
  public Response getEnvConfigHtml(@PathParam("env") String env) {
	  OasisConsoleService service = (OasisConsoleService) SpringApplicationContext.getBean("OasisConsoleServiceImpl");
	  LOGGER.debug("service = " + service);
	  OasisConfigType config = service.getConfig(env);
	  LOGGER.debug("config = " + config);
	  String html = OasisConsoleUtil.translateConfig2Html(config, env);
	  return Response.ok().entity(html).build();
  }
  
  @GET
  @Path("/config")
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.TEXT_HTML})
  public Response getConfig() {
	  OasisConsoleService service = (OasisConsoleService) SpringApplicationContext.getBean("OasisConsoleServiceImpl");
	  //LOGGER.debug("service = " + service);
	  List<String> envSupported = OasisConsoleUtil.getEnvironments();
	  
	  EnvConfigType envConfigType = new EnvConfigType();
	  for (String env : envSupported) {
		  LOGGER.debug("Running for env : " + env);
		  OasisConfigType config = service.getConfig(env);
		  //config.setEnv(env);
		  LOGGER.debug("config = " + config);		  
		  envConfigType.addConfig(config);
	  }
	  return Response.ok().entity(envConfigType).build();
  }
  
  @GET
  @Path("/htmlconfig")
  @Produces({MediaType.TEXT_HTML})
  public Response getConfigHtml() {
	  OasisConsoleService service = (OasisConsoleService) SpringApplicationContext.getBean("OasisConsoleServiceImpl");
	  //LOGGER.debug("service = " + service);
	  List<String> envSupported = OasisConsoleUtil.getEnvironments();
	  String html = "<html><header><title>Oasis Config</title></header>"
				+ "<body><h1>Oasis DB Configuration</h1><hr><br>";
	  StringBuilder sb = new StringBuilder();
	  for (String env : envSupported) {
		  OasisConfigType config = service.getConfig(env);
		  sb.append("<p></p>").append("<h2>").append(config.getEnv()).append("</h2>");
		  sb.append(OasisConsoleUtil.translateConfigBlock2Html(config, env));
	  }
	  html = html + sb.toString() + "</body></html>";

	  return Response.ok().entity(html).build();
  }

}

