package com.blog.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

import com.blog.demo.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
//https://stackoverflow.com/questions/67086865/spring-boot-with-mulitple-authentication-profiles?noredirect=1&lq=1
public class SecurityConfig {
	
	@Configuration
    @Profile({"test"})
	public class TestSecurityConfig extends WebSecurityConfigurerAdapter{
		
		@Autowired
		private UserDetailsService userDetailsService;
		
		@Bean(BeanIds.AUTHENTICATION_MANAGER)
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
		
		@Bean
		public JwtAuthenticationFilter jwtAuthenticationFilter() {
			return new JwtAuthenticationFilter();
		}
		
		@Bean
		public FilterExceptionHandler filterExceptionHandler() {
			return new FilterExceptionHandler();
		}
		
		@Override
		public void configure(HttpSecurity httpSecurity) throws Exception {
			httpSecurity.csrf().disable() //disable 'csrf'
				.authorizeRequests() //allow requests per below conditions
				// requests starting with '/api/auth'
				// permit everyone to send requests
				// allow all requests
				.antMatchers("/api/auth/**").permitAll()
				.antMatchers("/api/posts/**").permitAll()
				// the sender must be authenticated user
				.anyRequest().authenticated();
			
			httpSecurity.addFilterBefore(filterExceptionHandler(), WebAsyncManagerIntegrationFilter.class);
			httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		}
		
		// https://stackoverflow.com/questions/70581530/error-creating-bean-with-name-securityconfig-requested-bean-is-currently-in-c/71224432#71224432
		public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
			authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(encoder());
		}
		
		@Bean
		PasswordEncoder encoder() {
			return new BCryptPasswordEncoder();
		}
		
	}
	
	@Configuration
    @Profile({"dev"})
	public class DevSecurityConfig extends WebSecurityConfigurerAdapter{
		
		@Autowired
		private UserDetailsService userDetailsService;
		
		@Bean(BeanIds.AUTHENTICATION_MANAGER)
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
		
		@Bean
		public JwtAuthenticationFilter jwtAuthenticationFilter() {
			return new JwtAuthenticationFilter();
		}
		
		@Bean
		public FilterExceptionHandler filterExceptionHandler() {
			return new FilterExceptionHandler();
		}
		
		@Override
		public void configure(HttpSecurity httpSecurity) throws Exception {
			
			httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
	        .authorizeRequests().antMatchers("/console/**").permitAll();
			httpSecurity.csrf().disable();
			httpSecurity.headers().frameOptions().disable();
			
			httpSecurity.addFilterBefore(filterExceptionHandler(), WebAsyncManagerIntegrationFilter.class);
			httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		}
		
		// https://stackoverflow.com/questions/70581530/error-creating-bean-with-name-securityconfig-requested-bean-is-currently-in-c/71224432#71224432
		public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
			authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(encoder());
		}
		
		@Bean
		PasswordEncoder encoder() {
			return new BCryptPasswordEncoder();
		}
		
	}

}