package com.brushmyskills.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.brushmyskills.security.service.CustomUserDetailsService;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		 http.requestMatchers()
         .antMatchers("/login", "/oauth/authorize")
         .and()
         .authorizeRequests()
         .anyRequest()
         .authenticated()
         .and()
         /*Currently we are using spring provided login page*/
         .formLogin()
         /*If require our own custom login page*/
		 //.formLogin().loginPage("/customLoginPageUrl");
         //Allowing everybody to use form login
         .permitAll();
	}
	


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //Need to change only this piece of code for MySql based authentication
		auth.parentAuthenticationManager(authenticationManager)
              .userDetailsService(customUserDetailsService);
    }

}
