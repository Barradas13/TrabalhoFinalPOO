package br.edu.ifpr.cars.domain;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Passageiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank(message = "É necessário digitar um nome")
    @Size(min = 2, max=50, message = "Tamanho deve ser entre 3 e 50 caracteres")
    String name;
    LocalDate birthDate;
}
