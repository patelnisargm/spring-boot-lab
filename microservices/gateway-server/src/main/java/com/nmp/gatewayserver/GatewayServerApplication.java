package com.nmp.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

	@Bean
	public RouteLocator bankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {

		String gmtDateTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("GMT"))
				.format(DateTimeFormatter.RFC_1123_DATE_TIME);

		return routeLocatorBuilder.routes()
				.route(p -> p.path("/bank/account/**")
						.filters(f -> f.rewritePath("/bank/account/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", gmtDateTime))
						.uri("lb://account"))
				.route(p -> p.path("/bank/loan/**")
						.filters(f -> f.rewritePath("/bank/loan/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", gmtDateTime))
						.uri("lb://loan"))
				.route(p -> p.path("/bank/card/**")
						.filters(f -> f.rewritePath("/bank/card/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", gmtDateTime))
						.uri("lb://card"))
				.build();
	}

}
