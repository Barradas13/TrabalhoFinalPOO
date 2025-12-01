package br.edu.ifpr.cars.domain;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import jakarta.persistence.EnumType;

@Entity
@Data
public class Viagem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private String origem;
    
    @NotNull
    private String destino;
    
    private LocalDateTime dataHora;
    
    private Double preco;
    
    // MUITAS viagens pertencem a UM motorista
    @ManyToOne
    @JoinColumn(name = "motorista_id")
    @NotNull(message = "Motorista é obrigatório")
    private Driver motorista;
    
    // UMA viagem pode ter MUITOS passageiros
    // MUITOS passageiros podem estar em MUITAS viagens
    @ManyToMany
    @JoinTable(
        name = "viagem_passageiro", // Nome da tabela intermediária
        joinColumns = @JoinColumn(name = "viagem_id"),
        inverseJoinColumns = @JoinColumn(name = "passageiro_id")
    )
    private List<Passageiro> passageiros;
}