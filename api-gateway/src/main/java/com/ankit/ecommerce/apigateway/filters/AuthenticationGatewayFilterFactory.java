package com.ankit.ecommerce.apigateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<Config>{

    public AuthenticationGatewayFilterFactory(JwtService jwtService){
        super(Config.class);
        this.jwtService=jwtService;
    }

    @Override
    public GatewayFilter apply(Config config){
        return (exchange, chain) -> {

            if(!config.isEnabled) return chain.filter(exchange);
            String authorizationHeaders=exchange.getRequest().getHeaders().getFirst("Authorization");
            if(authorizationHeaders==null){
                exchange.getResponse().setStatusCode(HttpStatuc.UNAUTHORIZED);
                return exchange.getReponse().setComplete();
            }
            String token=authorizationHeaders.split("Bearer ")[1];

            Long userId=jwtService.getUserIdFromToken(token);

            exchange.getRequest()
                    .mutate()
                    .header("X-User-Id", userId.toString())
                    .build();

            return chain.filter(exchange);
        };
    }

    @Data
    public static class Config{
        private boolean isEnabled;
    }
}