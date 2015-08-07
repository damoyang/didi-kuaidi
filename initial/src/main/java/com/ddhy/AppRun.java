package com.ddhy;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ddhy.activemq.Sender;
@SpringBootApplication
public class AppRun extends SpringBootServletInitializer{
	static ConfigurableApplicationContext context;
	@Bean // Strictly speaking this bean is not necessary as boot creates a default
    JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
	 @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	       return application.sources(AppRun.class);
	}
	public static void main(String[] args) {
        context=SpringApplication.run(AppRun.class, args);
        Sender sender=new Sender();
        sender.send();
    }
	public static ConfigurableApplicationContext getContext() {
		// TODO Auto-generated method stub
		return context;
	}
}
