package org.jsanz.front.config;

import java.util.Locale;

import org.jsanz.core.batch.config.SpringBatchConfiguration;
import org.jsanz.core.config.DatabaseConfig;
import org.jsanz.front.view.MainWindows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringInicializer {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		
		ApplicationContext context = new AnnotationConfigApplicationContext(FrontConfig.class,DatabaseConfig.class,SpringBatchConfiguration.class);
		MainWindows mainWindows = context.getBean(MainWindows.class);
		
		
	}
}
