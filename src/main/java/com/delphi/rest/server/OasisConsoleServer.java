/**
 * 
 */
package com.delphi.rest.server;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
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
import com.delphi.rest.util.OasisConsoleUtil;

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

		// Create the ResourceHandler. It is the object that will actually handle the request for a given file. It is
		// a Jetty Handler object so it is suitable for chaining with other handlers as you will see in other examples.
		//ResourceHandler resource_handler = new ResourceHandler();
		// Configure the ResourceHandler. Setting the resource base indicates where the files should be served out of.
		// In this example it is the current directory but it can be configured to anything that the jvm has access to.
		/*resource_handler.setDirectoriesListed(true);
		resource_handler.setWelcomeFiles(new String[]{ "index.html" });
		resource_handler.setResourceBase(".");
		resource_handler.setMinMemoryMappedContentLength(-1);*/
		
		Server server = new Server(2222);
		
		ResourceConfig config = new ResourceConfig();
		config.packages("com.delphi.rest");
		config.register(OasisConsoleConfig.class);
		config.register(JsonMappingExceptionMapper.class);
		config.register(JsonParseExceptionMapper.class);

        ServletContextHandler restContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        restContext.setContextPath("/rest");
        restContext.addServlet(new ServletHolder(new ServletContainer(config)), "/*");
        /*context0.addServlet(new ServletHolder(new HelloServlet()),"/*");
        context0.addServlet(new ServletHolder(new HelloServlet("Buongiorno Mondo")),"/it/*");
        context0.addServlet(new ServletHolder(new HelloServlet("Bonjour le Monde")),"/fr/*");*/
 
        String jetty_home = 
                System.getProperty("jetty.home","../jetty-distribution/target/distribution");
        System.setProperty("jetty.home",jetty_home);
              
        WebAppContext webAppContext = new WebAppContext();        
        webAppContext.setContextPath("/");
		webAppContext.setResourceBase(".");        
		webAppContext.setWelcomeFiles(new String[]{ "index.html" });
		webAppContext.setInitParameter(
		        "org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");		
        //webapp.setWar(jetty_home+"/webapps/oasisconsole.war");
 
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[] { restContext, webAppContext });
 
        server.setHandler(contexts);
        
		/*ServletHolder servlet = new ServletHolder(new ServletContainer(config));
		ServletContextHandler context = new ServletContextHandler(server, "/*");
		context.addServlet(servlet, "/*");*/
		
		/*WebAppContext webAppContext = new WebAppContext();
		webAppContext.setInitParameter(
				        "org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
		//webAppContext.addServlet(servlet, "/");
		webAppContext.setWelcomeFiles(new String[]{ "index.html" });
		webAppContext.setResourceBase(".");
		server.setHandler(webAppContext);*/
		
		//context.addServlet(servlet, "/app/*");
		// Add the ResourceHandler to the server.
		/*HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { resource_handler, new DefaultHandler() });
		server.setHandler(handlers);*/

		LOGGER.info("Server starting in port 2222");
		try {
			ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");	
			ctx.registerShutdownHook();
			
			//LOGGER.debug(OasisConsoleUtil.getAllProperties().toString());
			LOGGER.debug(OasisConsoleUtil.getEnvironments().toString());
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
