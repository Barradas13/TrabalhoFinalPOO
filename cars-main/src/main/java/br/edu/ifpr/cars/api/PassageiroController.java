package br.edu.ifpr.cars.api;

import br.edu.ifpr.cars.domain.Driver;
import br.edu.ifpr.cars.domain.Passageiro;
import br.edu.ifpr.cars.domain.PassageiroRepository;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import br.edu.ifpr.cars.domain.Passageiro;
import br.edu.ifpr.cars.domain.PassageiroRepository;

@Service
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PassageiroController {
    
    @Autowired
    PassageiroRepository passageiroRepository;


    @GetMapping("/passageiro")
    public List<Passageiro> listPassageiros() {
        return passageiroRepository.findAll();
    }

    @GetMapping("/passageiro/{id}")
    public Passageiro findPassageiro(@PathVariable("id") Long id) {
        return passageiroRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/passageiro")
    public Passageiro createPassageiro(@RequestBody @Valid Passageiro passageiro) {
        return passageiroRepository.save(passageiro);
    }

    @PutMapping("/passageiro/{id}")
    public Passageiro fullUpdatePassageiro(@PathVariable("id") Long id, @RequestBody Passageiro passageiro) {
        Passageiro foundPassageiro = findPassageiro(id);
        foundPassageiro.setName(passageiro.getName());
        foundPassageiro.setBirthDate(passageiro.getBirthDate());
        return passageiroRepository.save(foundPassageiro);
    }

}
