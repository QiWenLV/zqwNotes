package com.zqw.webflux_demo.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.function.BiFunction;

/**
 * @Classname CalculatorHandler
 * @Description 函数式编程模型
 * @Date 2019/7/26 15:11
 * @Created by zqw
 * @Version 1.0
 */
@Component
public class CalculatorHandler {

    // 测试地址 http://127.0.0.1:8080/calculator?operator=add&v1=2&v2=3

    public Mono<ServerResponse> add(final ServerRequest request){
        return calculate(request, (v1, v2) -> v1 + v2);
    }

    public Mono<ServerResponse> sub(final ServerRequest request) {
        return calculate(request, (v1, v2) -> v1 - v2);
    }

    public Mono<ServerResponse> mul(final ServerRequest request) {
        return calculate(request, (v1, v2) -> v1 * v2);
    }

    public Mono<ServerResponse> div(final ServerRequest request) {
        return calculate(request, (v1, v2) -> v1 / v2);
    }


    private Mono<ServerResponse> calculate(final ServerRequest request,
                                           final BiFunction<Integer, Integer, Integer> calculateFunc){
        final Tuple2<Integer, Integer> operands = extractOperands(request);
        return ServerResponse.ok()
                .body(Mono.just(calculateFunc.apply(operands.getT1(), operands.getT2())), Integer.class);
    }

    /**
     * 参数封装为元组
     * @param request
     * @return
     */
    private Tuple2<Integer, Integer> extractOperands(final ServerRequest request){
        return Tuples.of(parseOperand(request, "v1"), parseOperand(request, "v2"));
    }


    /**
     * 解析参数
     * @param request
     * @param param
     * @return
     */
    private int parseOperand(final ServerRequest request, final String param) {
        try {
            return Integer.parseInt(request.queryParam(param).orElse("0"));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
