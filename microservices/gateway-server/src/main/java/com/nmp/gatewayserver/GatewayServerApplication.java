package com.nmp.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.*;
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
                .route(
                        booleanSpec -> booleanSpec.path("/bank/account/**")
                                .filters(
                                        gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/bank/account/(?<segment>.*)", "/${segment}")
                                                .addResponseHeader("X-Response-Time", gmtDateTime)
                                                .circuitBreaker(
                                                        config -> config.setName("accountCircuitBeaker")
                                                                .setFallbackUri("forward:/contactSupport")
                                                )
                                )
                                .uri("lb://account")
                )
                .route(
                        p -> p.path("/bank/loan/**")
                                .filters(
                                        gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/bank/loan/(?<segment>.*)", "/${segment}")
                                                .addResponseHeader("X-Response-Time", gmtDateTime)
                                                .retry(
                                                        retryConfig -> retryConfig.setMethods(HttpMethod.GET)
                                                                .setRetries(3)
                                                                .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)
                                                )
                                )
                                .uri("lb://loan")
                )
                .route(
                        p -> p.path("/bank/card/**")
                                .filters(
                                        gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/bank/card/(?<segment>.*)", "/${segment}")
                                                .addResponseHeader("X-Response-Time", gmtDateTime)
                                                .requestRateLimiter(
                                                        config -> config.setKeyResolver(userKeyResolver())
                                                                .setRateLimiter(redisRateLimiter())
                                                )
                                )
                                .uri("lb://card")
                )
                .build();
    }

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(1, 1, 1);
    }

    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
                .defaultIfEmpty("anonymous");
    }

}
