package br.com.hivecloud.transportadora.web.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	protected static final String[] AUTHORIZED_PATTERNS = { "/api//**" };

	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		RequestMatcher corsMatcher = CorsUtils::isPreFlightRequest;

		http.cors().and().csrf().disable().httpBasic().and().authorizeRequests().antMatchers(AUTHORIZED_PATTERNS).permitAll().requestMatchers(corsMatcher).permitAll();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
		config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
		source.registerCorsConfiguration("/**", config);
		
		return source;
	}
	

}
