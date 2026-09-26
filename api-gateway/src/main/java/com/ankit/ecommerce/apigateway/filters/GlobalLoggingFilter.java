package com.ankit.ecommerce.apigateway.filters;

@Component
@Slf4j
public class GlobalLoggingFilter implements GlobalFilter, Ordered{

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain){
        // pre-filter
        log.info("Loggin from Global: {}", exchange.getRequest().getURI());
        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            log.info("Logging from Global Post: {}", exchange.getResponse().getStatusCode());
        }));
    }

    @Override
    public int getOrder(){
        return 5;
    }

}