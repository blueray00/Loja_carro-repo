package br.org.edu.ifrn.LojaCarro.controllers;

import br.org.edu.ifrn.LojaCarro.dto.LoginRequest;
import br.org.edu.ifrn.LojaCarro.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        if ("gerente".equals(request.getUsername()) &&
                "123456".equals(request.getPassword())) {
            return JwtUtil.generateToken(request.getUsername(), "GERENTE");
        }

        if ("vendedor".equals(request.getUsername()) &&
                "123456".equals(request.getPassword())) {
            return JwtUtil.generateToken(request.getUsername(), "VENDEDOR");
        }

        throw new RuntimeException("Usuário ou senha inválidos");
    }
}
