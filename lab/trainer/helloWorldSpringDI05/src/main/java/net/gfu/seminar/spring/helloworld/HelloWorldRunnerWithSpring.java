package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * HelloWorld using Spring Framework Dependency Injection.
 * 
 * @author tf
 * @see <a href="http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/beans.html#beans-introduction">Spring DI</a>
 */
public class HelloWorldRunnerWithSpring {

	public static void main(String[] args) {
		/// XXX Examples for creating the Spring Container
		// 1. The Bean Factory
		// loading the Spring XML configuration file and initializing the BeanFactory
		// this file is located in the classpath. 
		
		// this creates a new instance of the Spring Container aka BeanFactory
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml", //located in src/main/resources 
				"testData.xml");//located in src/test/resources
		
		// here we retrieve an instance of Greeting from the Spring BeanFactory
		GreetingService reception = (GreetingService) beanFactory.getBean("welcome");
		
		System.out.println(reception.welcome());

	}

}
