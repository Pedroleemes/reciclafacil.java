package br.com.fiap.reciclafacil.service;

import br.com.fiap.reciclafacil.model.PontoColeta;
import br.com.fiap.reciclafacil.repository.PontoColetaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PontoColetaService {

    private final PontoColetaRepository repository;

    public PontoColetaService(PontoColetaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public String registrarDescarte(Long id, Double peso) {
        PontoColeta ponto = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ponto de coleta não encontrado com o ID: " + id));

        ponto.setCapacidadeAtualKg(ponto.getCapacidadeAtualKg() + peso);

        repository.save(ponto);

        if (ponto.atingiuLimite()) {
            return "Descarte registrado! [ALERTA ESG] O ponto de coleta '" + ponto.getNome() + "' atingiu o limite! Notificando caminhão de coleta automatizada.";
        }

        return "Descarte efetuado com sucesso! Obrigado por destinar corretamente seu material: " + ponto.getTipoMaterial();
    }
    public List<PontoColeta> listarTodos() {
        return repository.findAll();
    }
}