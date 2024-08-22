package achmad.rifai.apigateway.configs;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
//@EnableWebFluxSecurity
public class ApiConfig {

//	@Bean
//	SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
//		return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
//				.authorizeExchange(exchange ->exchange
//						.pathMatchers("/api/user/login", "/api/user/test").permitAll()
//						.anyExchange().authenticated()
//				)
//				.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
//				.build();
//	}

	@Bean
	RouteLocator route(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(route -> route
						.path("/api/user/**")
						.filters(filter -> filter.rewritePath("/api/user/(?<segment>.*)", "/$\\\\{segment}"))
						.uri("lb://user-service")
					)
				.build();
	}

}
