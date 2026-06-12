package br.org.edu.ifrn.LojaCarro.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @PostMapping
    public ResponseEntity<String> cadastrarUsuario() {
        return ResponseEntity.ok("Usuário cadastrado");
    }
}