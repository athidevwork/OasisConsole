/**
 * 
 */
package com.delphi.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.delphi.rest.config.SpringApplicationContext;
import com.delphi.rest.entity.policy.PolicyDataType;
import com.delphi.rest.service.IssueService;
import com.delphi.rest.util.OasisConsoleUtil;

/**
 * @author Athi
 *
 */
@Path("/oasisissue")
public class IssueResource {
	static Logger LOGGER = LoggerFactory.getLogger(IssueResource.class);

	@GET
	@Path("/home")
	public String sayHello() {
		return "Hello from Oasis Issue";
	}

	@GET
	@Path("/policy")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.TEXT_HTML })
	public Response getPolicyData(@HeaderParam("accept") String accept, @QueryParam("env") String env, @QueryParam("policy") String policy) {
		LOGGER.debug("accept header = " + accept);
		IssueService service = (IssueService) SpringApplicationContext.getBean("IssueServiceImpl");
		LOGGER.debug("service = " + service);
		PolicyDataType config = service.getPolicyData(env, policy);
		LOGGER.debug("config = " + config);
		return Response.ok().entity(config).build();
	}
	
	@GET
	@Path("/html/policy")
	@Produces({ MediaType.TEXT_HTML })
	public Response getEnvConfigHtml(@QueryParam("env") String env, @QueryParam("policy") String policy) {
		IssueService service = (IssueService) SpringApplicationContext.getBean("IssueServiceImpl");
		LOGGER.debug("service = " + service);
		PolicyDataType policyData = service.getPolicyData(env, policy);
		LOGGER.debug("policyData = " + policyData);
		String html = OasisConsoleUtil.translatePolicy2Html(policyData, env);
		return Response.ok().entity(html).build();
	}	
}
