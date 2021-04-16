package org.sid.lightecomv1.sec;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/categories/**").permitAll() ;
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/products/**").permitAll() ;
        http.authorizeRequests().antMatchers(HttpMethod.PATCH,"/products/**").permitAll() ;
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/photoProduct/**").permitAll() ;
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/uploadPhoto/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/addProduct/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/orders/**").permitAll();

        //  http.authorizeRequests().antMatchers("/categories/**").hasAuthority("ADMIN");
        //http.authorizeRequests().antMatchers("/products/**").hasAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilterBefore(new JWTAutorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
