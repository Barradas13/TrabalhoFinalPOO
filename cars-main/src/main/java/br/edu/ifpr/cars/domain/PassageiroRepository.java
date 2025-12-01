package br.edu.ifpr.cars.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PassageiroRepository  extends JpaRepository<Passageiro, Long>  {
    
}
