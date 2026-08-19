package br.com.fiap.reciclafacil.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamento-coleta")
public class AgendamentoController {

    // Nota: Em um projeto real, você injetaria o AgendamentoService aqui.
    // Como é um MVP para a faculdade, vamos simular a resposta para cumprir os verbos REST.

    // Endpoint 3: Atualizar o status de um agendamento de caminhão (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarAgendamento(@PathVariable Long id, @RequestBody String novoStatus) {
        // Lógica simulada de atualização
        return ResponseEntity.ok("Agendamento da coleta " + id + " atualizado para o status: " + novoStatus);
    }

    // Endpoint 4: Cancelar um agendamento de coleta (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelarAgendamento(@PathVariable Long id) {
        // Lógica simulada de exclusão
        return ResponseEntity.ok("Agendamento de coleta " + id + " cancelado com sucesso por motivos operacionais.");
    }
}