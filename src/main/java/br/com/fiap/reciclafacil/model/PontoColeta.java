package br.com.fiap.reciclafacil.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_ponto_coleta")
@Data
public class PontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;

    @Column(name = "tipo_material")
    private String tipoMaterial;

    @Column(name = "capacidade_maxima_kg")
    private Double capacidademaximaKg;

    @Column(name = "capacidade_atual_kg")
    private Double capacidadeAtualKg;

    public boolean atingiuLimite() {
        return this.capacidadeAtualKg >= this.capacidademaximaKg;
    }
}