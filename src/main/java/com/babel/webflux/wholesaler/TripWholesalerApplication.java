package com.babel.webflux.wholesaler;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@SpringBootApplication
public class TripWholesalerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripWholesalerApplication.class, args);
	}

	@Bean
	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

		var initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);

		return initializer;
	}


}
