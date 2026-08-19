package br.com.fiap.reciclafacil.controller;

import br.com.fiap.reciclafacil.dto.DescarteRequestDto;
import br.com.fiap.reciclafacil.model.PontoColeta;
import br.com.fiap.reciclafacil.service.PontoColetaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pontos-coleta")
public class PontoColetaController {

    private final PontoColetaService service;

    public PontoColetaController(PontoColetaService service) {
        this.service = service;
    }


    @PostMapping("/{id}/registrar-descarte")
    public ResponseEntity<String> registrarDescarte(
            @PathVariable Long id,
            @RequestBody @Valid DescarteRequestDto dto) {

        String mensagemResultado = service.registrarDescarte(id, dto.pesoKg());
        return ResponseEntity.ok(mensagemResultado);
    }

    @GetMapping
    public ResponseEntity<List<PontoColeta>> listarPontosColeta() {
        return ResponseEntity.ok(service.listarTodos());
    }
}