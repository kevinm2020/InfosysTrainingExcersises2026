package com.infosys.irs.security;

// =======================
// Spring Security imports
// =======================
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotationConfiguration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security configuration class.
 *
 * This class:
 * - Defines authentication (who the user is)
 * - Defines authorization (what the user can access)
 */
@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Custom UserDetailsService.
     *
     * Spring Security uses this to load user data
     * (username, password, roles) from the database.
     */
    @Autowired
    private InfyGoUserDetailsService userDetailsService;

    /**
     * Configure authentication.
     *
     * Tells Spring Security:
     * "Use my custom UserDetailsService to authenticate users."
     */
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        // Register custom user details service
        auth.userDetailsService(userDetailsService);
    }

    /**
     * Configure authorization and login behavior.
     *
     * Defines:
     * - Which URLs are public
     * - Which URLs require authentication
     * - Login and logout behavior
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            // Authorization rules
            .authorizeRequests()

                // Publicly accessible URLs (no login required)
                .antMatchers("/", "/login", "/register", "/imgs/**").permitAll()

                // Any other request requires authentication
                .anyRequest().authenticated()
            .and()

            // Form-based login configuration
            .formLogin()
                .loginPage("/login")                // Custom login page
                .defaultSuccessUrl("/flights", true) // Redirect after successful login
                .permitAll()
            .and()

            // Logout configuration
            .logout()
                .permitAll();
    }

    /**
     * Password encoder bean.
     *
     * BCrypt:
     * - Secure
     * - Salted
     * - Industry standard
     *
     * Used to hash passwords before storing
     * and to verify passwords during login.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
