/**
 * 
 */
package com.avik.springbootdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author Avik
 *
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/", "/home", "/about").permitAll()
				.antMatchers("/admin/**").hasAnyRole("ADMIN").antMatchers("/user/**").hasAnyRole("USER").anyRequest()
				.authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().permitAll().and()
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}

	// create two users, admin and user
	@SuppressWarnings("deprecation")
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("user")
				.password("password").roles("USER").and().withUser("admin").password("password").roles("ADMIN");
	}

}
