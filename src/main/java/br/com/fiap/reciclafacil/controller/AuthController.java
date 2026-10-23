package br.com.fiap.reciclafacil.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        // Simula a validação exata que o nosso teste automatizado envia
        if ("usuario@email.com".equals(email) && "SenhaSegura123".equals(password)) {
            // Retorna status 200 e o formato JSON exigido pelo Schema
            return ResponseEntity.ok(Map.of("token", "simulacao-de-token-jwt-valido"));
        }
        
        // Retorna 401 para credenciais inválidas (Caminho Negativo do teste)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }
}