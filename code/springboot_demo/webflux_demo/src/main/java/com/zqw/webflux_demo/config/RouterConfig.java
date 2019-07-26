package com.zqw.webflux_demo.config;

import com.sun.el.util.ReflectionUtil;
import com.zqw.webflux_demo.controller.CalculatorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

/**
 * @Classname RouterConfig
 * @Description TODO
 * @Date 2019/7/26 15:26
 * @Created by zqw
 * @Version 1.0
 */
@Configuration
public class RouterConfig {

    @Autowired
    @Bean
    public RouterFunction<ServerResponse> responseRouterFunction(final CalculatorHandler calculatorHandler){
        return RouterFunctions.route(RequestPredicates.path("/calculator"), request ->
                request.queryParam("operator").map(operator ->
                        Mono.justOrEmpty(ReflectionUtils.findMethod(CalculatorHandler.class, operator, ServerRequest.class))
                            .flatMap(method -> (Mono<ServerResponse>) ReflectionUtils.invokeMethod(method, calculatorHandler, request))
                            .switchIfEmpty(ServerResponse.badRequest().build())
                            .onErrorResume(ex -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build()))
                .orElse(ServerResponse.badRequest().build()));
    }
}
