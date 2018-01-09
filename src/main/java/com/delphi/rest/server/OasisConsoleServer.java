/**
 * 
 */
package com.delphi.rest.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.delphi.rest.config.OasisConsoleConfig;
import com.delphi.rest.exception.JsonMappingExceptionMapper;
import com.delphi.rest.exception.JsonParseExceptionMapper;
import com.delphi.rest.resource.OasisConsoleResource;

/**
 * @author Athi
 *
 */
@ComponentScan("com.delphi.rest")
public class OasisConsoleServer {
	static Logger LOGGER = LoggerFactory.getLogger(OasisConsoleResource.class);
	
	public static void main(String[] args) {
		LOGGER.info("Hello from Oasis Console Server");
		AbstractApplicationContext ctx = null;		   
		ResourceConfig config = new ResourceConfig();
		config.packages("com.delphi.rest");
		config.register(OasisConsoleConfig.class);
		config.register(JsonMappingExceptionMapper.class);
		config.register(JsonParseExceptionMapper.class);

		ServletHolder servlet = new ServletHolder(new ServletContainer(config));

		Server server = new Server(2222);
		ServletContextHandler context = new ServletContextHandler(server, "/*");
		context.addServlet(servlet, "/*");

		LOGGER.info("Server starting in port 2222");
		try {
			ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");	
			ctx.registerShutdownHook();
			
		    server.start();
		    server.join();
		 } catch (Exception e) {
			e.printStackTrace();
		} finally {
			ctx.close();
		    server.destroy();
		    LOGGER.info("Server destroyed");
		}		
	}

}
