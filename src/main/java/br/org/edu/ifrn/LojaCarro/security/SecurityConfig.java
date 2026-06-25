package br.org.edu.ifrn.LojaCarro.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // desabilita CSRF para facilitar testes
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll() // libera login/registro
                        .requestMatchers("/carro/**").authenticated() // exige login para CRUD
                )
                .httpBasic(); // autenticação básica (temporária, até configurar JWT)

        return http.build();
    }
}
