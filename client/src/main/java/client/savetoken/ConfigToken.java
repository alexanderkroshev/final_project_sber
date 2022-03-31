package client.savetoken;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfigToken {
    @Bean
    public TokenProvider tokenProvider(){
        return new TokenProvider();
    }
}
