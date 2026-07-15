package br.org.edu.ifrn.lojacarro.controllers;

import br.org.edu.ifrn.lojacarro.dto.LoginRequest;
import br.org.edu.ifrn.lojacarro.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest login) {

        if ("gerente".equals(login.getUsername()) &&
                "123".equals(login.getPassword())) {

            return ResponseEntity.ok(
                    jwtService.gerarToken("gerente", "GERENTE"));
        }

        if ("vendedor".equals(login.getUsername()) &&
                "123".equals(login.getPassword())) {

            return ResponseEntity.ok(
                    jwtService.gerarToken("vendedor", "VENDEDOR"));
        }

        return ResponseEntity.status(401)
                .body("Usuário ou senha inválidos");
    }
}