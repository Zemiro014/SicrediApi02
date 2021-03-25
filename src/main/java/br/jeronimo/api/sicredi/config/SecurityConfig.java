package br.jeronimo.api.sicredi.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.jeronimo.api.sicredi.security.JWTAuthenticationFilter;
import br.jeronimo.api.sicredi.security.JWTAuthorizationFilter;
import br.jeronimo.api.sicredi.security.JWTUtil;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final UserDetailsService userDetailsService; 
	private final JWTUtil jwtUtil;

	private static final String[] PUBLIC_MATCHERS_GET = { "/guidelines/**", "/votingSessions/**"};
	private static final String[] PUBLIC_MATCHERS_POST = { "/associates/**"};

	@Override
	  protected void configure(HttpSecurity http) throws Exception {
		  http.cors().and().csrf().disable();
	    http.authorizeRequests()
	    .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
	    .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
	    .anyRequest().authenticated();
	    http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
	    http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
	    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	  }
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() 
	{
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST","GET", "PUT", "DELETE", "OPTIONS"));
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**",configuration);
	    return source;
	}
	
	 @Bean
	 public BCryptPasswordEncoder bCryptPasswordEncoder() 
	 {
		  return new BCryptPasswordEncoder();
	 }
	 
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception 
		{
			auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
		}
}
