
package com.toolshare.toolshare.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Security configuration class.
 * Configures authentication, authorization, and CORS.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * The CustomUserDetailsService dependency to be injected by Spring.
     */
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Configures authentication using the CustomUserDetailsService
     * and password encoder.
     *
     * @param auth The AuthenticationManagerBuilder object to configure
     * @throws Exception If an error occurs during configuration
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * Creates an AuthenticationManager bean for use in the application.
     *
     * @return The AuthenticationManager bean
     * @throws Exception If an error occurs while creating the bean
     */
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Configures HTTP security, including CORS, session creation policy,
     * and authorization rules.
     *
     * @param http The HttpSecurity object to configure
     * @throws Exception If an error occurs during configuration
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(
                SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/api/authentication/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/v3/api-docs/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/product").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/participant/**").authenticated()
                .antMatchers(HttpMethod.GET, "/api/participant/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/imagefile/**").permitAll()
                .antMatchers(HttpMethod.GET, "/download/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/items/**").authenticated()
                .antMatchers(HttpMethod.GET, "/api/items/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/loan/**").authenticated()
                .antMatchers(HttpMethod.GET, "/api/loan/**").authenticated()
                .anyRequest().authenticated();
        http.addFilterBefore(jwtAuthorizationFilter(),
                UsernamePasswordAuthenticationFilter.class);
    }


    /**
     * Creates a JwtAuthorizationFilter bean for use in the application.
     *
     * @return The JwtAuthorizationFilter bean
     */
    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter();
    }

    /**
     * Creates a BCryptPasswordEncoder bean for use in the application.
     *
     * @return The BCryptPasswordEncoder bean
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * The allowed origin value obtained from the application's properties
     * file using the @Value annotation.
     */
    @Value("${allowed.origin}")
    private String allowedOrigin;

    /**
     * Configures CORS settings for the application.
     *
     * @return The WebMvcConfigurer object to configure
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(final CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigin)
                        .allowedMethods("POST", "GET", "PUT", "DELETE")
                        .allowedHeaders("Content-Type", "Authorization")
                        .allowCredentials(true);
            }
        };
    }
}