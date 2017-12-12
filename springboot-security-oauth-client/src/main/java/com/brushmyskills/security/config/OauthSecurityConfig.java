package com.brushmyskills.security.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class OauthSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*
		 * In below code we are saying apart from root and login URL, 
		 * all other URL's should be authenticated and authorize using OAuth server
		 */
		http.antMatcher("/**")
		    .authorizeRequests()
		    .antMatchers("/", "/login**")
		    .permitAll()
		    .anyRequest()
			.authenticated();

	}

}
