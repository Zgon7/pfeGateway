package com.example.demo.security;

import com.example.demo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class SecurityConfigurer extends WebSecurityConfigurerAdapter  {

    @Autowired
    private CustomUserDetailsService customUserDetailService;
    @Autowired
    private JwtAuthenticationFilter authenticationFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /* http
                .addFilterBefore(new CORSFilter(), SessionManagementFilter.class);*/
        http.cors().and().
        csrf()
                .disable()
                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/api/category/add").hasRole("MANAGER")
//                .antMatchers(HttpMethod.PUT, "/api/category/update").hasRole("MANAGER")
//                .antMatchers(HttpMethod.DELETE, "/api/category/delete").hasRole("MANAGER")
//                .antMatchers(HttpMethod.POST, "/api/products/add").hasRole("MANAGER")
//                .antMatchers(HttpMethod.PUT, "/api/products/update").hasRole("MANAGER")
//                .antMatchers(HttpMethod.DELETE, "/api/products/delete").hasRole("MANAGER")
//                .antMatchers(HttpMethod.GET, "/api/user/activate/**").hasRole("MANAGER")
               // .antMatchers(HttpMethod.GET, "/api/users").hasRole("DIRPHARMACIE")
                .antMatchers("/api/**"
                        ,"/api/authenticate",
                        "/api/users"
                        ,"/api/signup"
                        ,"/api/**"
                        ,"/api/category/categoryById/**"
                        ,"/api/products/list"
                        , "/api/products/product/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService);
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

}
