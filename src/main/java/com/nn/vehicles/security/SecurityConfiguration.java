package com.nn.vehicles.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private UserPrincipalDetailsService userPrincipalDetailsService;
	
	public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService) {
		this.userPrincipalDetailsService = userPrincipalDetailsService;
	}

	@Override	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/customers/**").hasRole("ADMIN")
		.antMatchers("/customer").hasRole("CUSTOMER")
		.antMatchers("/repairs/customer/**").hasRole("CUSTOMER")
		.antMatchers("/repairs/book").hasRole("CUSTOMER")
		.antMatchers("/vehicles/customer/**").hasAnyRole("CUSTOMER", "ADMIN")
		.antMatchers("/employees/**").hasRole("ADMIN")
		.antMatchers("/employee").hasRole("EMPLOYEE")
		.antMatchers("/repairs/repairshop/**").hasRole("EMPLOYEE")
		.antMatchers("/repairs/process/**").hasRole("EMPLOYEE")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/register/employee").hasRole("ADMIN")
		.antMatchers("/register/admin").hasRole("ADMIN")
		.antMatchers("/brands/**").hasRole("ADMIN")
		.antMatchers("/brandmodels/**").hasRole("ADMIN")
		.antMatchers("/repairshops/**").hasRole("ADMIN")
		.antMatchers("/repairtypes/**").hasRole("ADMIN")
		.antMatchers("/qualifications/**").hasRole("ADMIN")
		.antMatchers("/repairs").hasRole("ADMIN")
		.antMatchers("/repairs/add").hasRole("ADMIN")
		.antMatchers("/repairs/edit/{id}").hasRole("ADMIN")
		.antMatchers("/repairs/delete/{id}").hasRole("ADMIN")
		.antMatchers("/vehicles").hasRole("ADMIN")
		.antMatchers("/vehicles/add").hasRole("ADMIN")
		.antMatchers("/vehicles/edit/{id}").hasRole("ADMIN")
		.antMatchers("/vehicles/delete/{id}").hasAnyRole("CUSTOMER", "ADMIN")
		.and()
		.formLogin()
		.loginPage("/login").permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);;
		
		return daoAuthenticationProvider;
	}
	
	

}
