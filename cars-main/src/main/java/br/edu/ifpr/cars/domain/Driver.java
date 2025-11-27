package br.edu.ifpr.cars.domain;

import java.time.LocalDate;

import br.edu.ifpr.cars.validate.Impar;
import br.edu.ifpr.cars.validate.Placa;
import br.edu.ifpr.cars.validate.CNH;
import br.edu.ifpr.cars.validate.AnoFabricacao;
import br.edu.ifpr.cars.validate.Comentario;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank(message = "É necessário digitar um nome")
    @Size(min = 2, max=50, message = "Tamanho deve ser entre 3 e 50 caracteres")
    String name;
    LocalDate birthDate;
    @Impar
    int numero;
    @Placa
    String placa;
    @CNH
    String CNH;
    @AnoFabricacao
    String anoFabricacao;
    @Comentario
    String comentario;

}
