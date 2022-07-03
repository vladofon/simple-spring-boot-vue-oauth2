package com.annoying.auth.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.annoying.auth.domain.User;
import com.annoying.auth.repository.UserDetailsRepo;

@EnableWebSecurity
@Configuration
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// add http.cors()
		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers("/", "/login**", "/js/**", "/error**", "/electronics/profile", "/sessions/me").permitAll()
				.anyRequest().authenticated().and().logout().logoutSuccessUrl("http://localhost:8081/").permitAll().and()
				.httpBasic();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.GET, "/electronics");
	}

	@Bean
	public PrincipalExtractor principalExtractor(UserDetailsRepo userDetailsRepo) {
		return map -> {
			String id = (String) map.get("sub");
			User user = userDetailsRepo.findById(id).orElseGet(() -> {
				User newUser = new User();

				newUser.setId(id);
				newUser.setName((String) map.get("name"));
				newUser.setEmail((String) map.get("email"));
				newUser.setGender((String) map.get("gender"));
				newUser.setLocale((String) map.get("locale"));
				newUser.setUserpic((String) map.get("picture"));
				return newUser;
			});
			user.setLastVisit(LocalDateTime.now());
			return userDetailsRepo.save(user);
		};
	}

	// To enable CORS
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();

		List<String> allowedOriginsUrl = new ArrayList<>();
		allowedOriginsUrl.add("http://localhost:8081/");
		allowedOriginsUrl.add("http://192.192.168.2.104:8081/");
		configuration.setAllowedOrigins(allowedOriginsUrl);
		configuration.setAllowedMethods((List.of("GET", "POST", "PUT", "DELETE")));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}
}
