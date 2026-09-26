package com.ankit.ecommerce.apigateway.filters;

@Component
@Slf4j
public class LoggingOrdersFilter extends AbstractGatewayFilterFactory<LoggingOrdersFilter.Config>{

    public LoggingOrdersFilter(Class<Config> configClass){
        super(configClass);
    }

    @Override
    public GatewayFilter apply(Config config){
        return (exchange, chain) -> {
            log.info("Order Filter Pre: {}", exchange.getRequest().getURI());
            return chain.filter(exchange);
        };
    }

    public static class Config{

    }
}