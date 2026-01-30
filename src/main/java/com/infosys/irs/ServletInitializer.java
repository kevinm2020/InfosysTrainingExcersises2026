package com.infosys.irs;

// Used to configure the application when deployed as a WAR
import org.springframework.boot.builder.SpringApplicationBuilder;

// Base class required to initialize Spring Boot in an external servlet container
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * ServletInitializer is used when deploying the application
 * as a WAR file to an external servlet container (e.g. Tomcat).
 *
 * This class is NOT required when running the app
 * as a standalone JAR.
 */
public class ServletInitializer extends SpringBootServletInitializer {

    /**
     * This method is called by the servlet container
     * (not by main()) when the application starts.
     *
     * It tells Spring which class should be used
     * to bootstrap the application.
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        /**
         * Register the main Spring Boot application class.
         *
         * InfyGoBootSpringMvcApplication contains:
         * - @SpringBootApplication
         * - Component scanning
         * - Auto-configuration
         */
        return application.sources(InfyGoBootSpringMvcApplication.class);
    }
}
