package com.example.solutionchallenge.business.configs;


import com.example.solutionchallenge.core.exceptions.CustomAccessDeniedHandler;
import com.example.solutionchallenge.core.exceptions.CustomAuthenticationFailureHandler;
import com.example.solutionchallenge.core.security.filters.CustomAuthenticationFilter;
import com.example.solutionchallenge.core.security.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v3/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/api/register/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors();
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/users/getall/**").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/users/save/**").hasAnyAuthority("admin").and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        http.authorizeRequests().antMatchers(POST, "/api/persons/add/**").hasAnyAuthority("admin").and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        http.authorizeRequests().antMatchers(POST, "/api/users/addroletouser/**").hasAnyAuthority("admin").and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        http.authorizeRequests().antMatchers(POST, "/api/users/getuserdetail/**").hasAnyAuthority("admin").and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        http.authorizeRequests().antMatchers(POST, "/api/reports/getallreportdetail/**").hasAnyAuthority().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }


    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
