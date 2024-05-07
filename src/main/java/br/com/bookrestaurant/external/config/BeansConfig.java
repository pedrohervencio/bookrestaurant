package br.com.bookrestaurant.external.config;

import br.com.bookrestaurant.infraestructure.controller.ReserveController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    public ReserveController reserveController() {
        return new ReserveController();
    }
}
