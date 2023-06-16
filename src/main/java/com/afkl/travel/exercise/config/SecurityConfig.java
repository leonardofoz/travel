package com.afkl.travel.exercise.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration class for securing endpoints with basic authentication.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${travel.user}")
    private String travelUser;

    @Value("${travel.password}")
    private String travelPassword;

    @Value("${management.endpoints.web.base-path:/actuator}")
    private String actuatorBasePath;

    @Value("${actuator.metrics.username}")
    private String metricsUsername;

    @Value("${actuator.metrics.password}")
    private String metricsPassword;

    /**
     * Configures the HTTP security for different endpoints.
     *
     * @param http the HttpSecurity object to configure
     * @throws Exception if an error occurs during configuration
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(actuatorBasePath + "/metrics").hasRole("ACTUATOR")
                .antMatchers("/api/**").hasRole("USER") // Example: Securing '/api/**' endpoints
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    /**
     * Configures the authentication manager for the basic authentication.
     *
     * @param auth the AuthenticationManagerBuilder object to configure
     * @throws Exception if an error occurs during configuration
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(metricsUsername)
                .password("{noop}" + metricsPassword)
                .roles("ACTUATOR")
                .and()
                .withUser(travelUser)
                .password("{noop}" + travelPassword)
                .roles("USER");
    }


}
