package com.coding.assessment.customerservice.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizedUrl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

	@Bean
	public PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource driverManagerSource = new DriverManagerDataSource();
		driverManagerSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		driverManagerSource.setUrl("jdbc:mysql://127.0.0.1:3306/customerdb");
		driverManagerSource.setUsername("root");
		driverManagerSource.setPassword("root");
		return driverManagerSource;
	}

	// JDBC Authenication
	@Bean
	public JdbcUserDetailsManager users(DataSource datasource) {
		return new JdbcUserDetailsManager(datasource);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests(authorize -> authorize.anyRequest().authenticated())
				.formLogin(withDefaults()).httpBasic(withDefaults());
		return http.build();
	}

}
