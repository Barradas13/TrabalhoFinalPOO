package br.edu.ifpr.cars.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {
    
    // Spring Data JPA cria automaticamente a implementação destes métodos
    // baseado nos nomes dos métodos (Query Methods)
    
    // Buscar viagens por motorista
    List<Viagem> findByMotoristaId(Long motoristaId);
    
    // Buscar viagens por passageiro (se usar 1 passageiro)
    List<Viagem> findByPassageiroId(Long passageiroId);
    
    // Buscar viagens por origem
    List<Viagem> findByOrigem(String origem);
    
    // Buscar viagens por destino
    List<Viagem> findByDestino(String destino);
    
    // Buscar viagens entre origem e destino
    List<Viagem> findByOrigemAndDestino(String origem, String destino);
    
    // Se usar List<Passageiro>, use este para buscar viagens de um passageiro:
    // List<Viagem> findByPassageirosId(Long passageiroId);
}