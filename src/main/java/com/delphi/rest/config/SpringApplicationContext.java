/**
 * 
 */
package com.delphi.rest.config;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author Athi
 *
 */
public class SpringApplicationContext implements ApplicationContextAware {
	// The spring application context.
	@Autowired
	private static ApplicationContext applicationContext;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException
	{
		System.out.println("Setting spring context = " + applicationContext);
		System.out.println("Beans = \n" + Arrays.asList(applicationContext.getBeanDefinitionNames()) + "\n");
	    
		SpringApplicationContext.applicationContext = applicationContext;
	}
	
	/**
	 * This method is used to retrieve a bean by its name. Note that this may
	 * result in new bean creation if the scope is set to "prototype" in the
	 * bean configuration file.
	 * 
	 * @param beanName
	 *            The name of the bean as configured in the spring configuration
	 *            file.
	 * @return The bean if it is existing or null.
	 */
	public static Object getBean(String beanName)
	{
		if (null == beanName)
		{
			return null;
		}
		return applicationContext.getBean(beanName);
	}
	
	public static Object getProperty(String property) {
		if (null == property)
			return null;
		return applicationContext.getEnvironment().getProperty(property);
	}
}
