package com.infosys.irs;

// =======================
// Spring Boot imports
// =======================

// Used to launch the Spring Boot application
import org.springframework.boot.SpringApplication;

// Core annotation that enables Spring Boot features
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Allows loading additional property files
import org.springframework.context.annotation.PropertySource;

/**
 * This is the MAIN entry point of the Spring Boot application.
 *
 * When this class runs:
 * - Spring initializes the application context
 * - Scans for controllers, services, repositories
 * - Loads configuration files
 * - Starts the embedded web server (Tomcat)
 */
@SpringBootApplication
@PropertySource("classpath:configuration.properties")
public class InfyGoBootSpringMvcApplication {

    /**
     * Main method – JVM starts execution here.
     *
     * @param args command-line arguments (rarely used directly)
     */
    public static void main(String[] args) {

        /**
         * SpringApplication.run():
         * - Bootstraps the Spring application
         * - Performs component scanning
         * - Applies auto-configuration
         * - Starts the embedded Tomcat server
         *
         * After this line runs, your application is LIVE.
         */
        SpringApplication.run(InfyGoBootSpringMvcApplication.class, args);
    }
}
