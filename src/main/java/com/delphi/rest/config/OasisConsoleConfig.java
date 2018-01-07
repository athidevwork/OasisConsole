/**
 * 
 */
package com.delphi.rest.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

/**
 * @author Athi
 *
 */
@ApplicationPath("/rest")
public class OasisConsoleConfig extends ResourceConfig {
  OasisConsoleConfig config;
	
  public OasisConsoleConfig() {
    property(ServerProperties.TRACING, "ALL");
    property(ServerProperties.TRACING_THRESHOLD, "VERBOSE");
  }
}

