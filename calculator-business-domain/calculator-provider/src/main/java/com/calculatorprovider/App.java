package com.calculatorprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The APM app starting point. This class starts the server and configures the
 * APM endpoint.
 */
@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
@EnableEurekaClient
public class App {
    /**
     * The main that starts the application.
     *
     * @param theArgs
     *            command line arguments to configure excecution.
     */
    public static void main(final String[] theArgs) {
        SpringApplication app = new SpringApplication(App.class);
        app.run(theArgs);
    }

   
}
