package br.edu.ifpr.cars.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifpr.cars.domain.Viagem;
import br.edu.ifpr.cars.domain.ViagemRepository;
import jakarta.validation.Valid;

@Service
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ViagemController {

    @Autowired
    private ViagemRepository viagemRepository;

    // Listar todas as viagens
    @GetMapping("/viagens")
    public List<Viagem> listViagens() {
        return viagemRepository.findAll();
    }

    // Buscar viagem por ID
    @GetMapping("/viagens/{id}")
    public Viagem findViagem(@PathVariable("id") Long id) {
        return viagemRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Viagem não encontrada"));
    }

    // Criar nova viagem
    @PostMapping("/viagens")
    public Viagem createViagem(@RequestBody @Valid Viagem viagem) {
        return viagemRepository.save(viagem);
    }

    // Atualização completa
    @PutMapping("/viagens/{id}")
    public Viagem fullUpdateViagem(@PathVariable("id") Long id,
            @RequestBody @Valid Viagem viagem) {
        Viagem foundViagem = findViagem(id);
        
        foundViagem.setOrigem(viagem.getOrigem());
        foundViagem.setDestino(viagem.getDestino());
        foundViagem.setDataHora(viagem.getDataHora());
        foundViagem.setPreco(viagem.getPreco());
        foundViagem.setMotorista(viagem.getMotorista());
        foundViagem.setPassageiro(viagem.getPassageiro());
        // Se usar List<Passageiro>: foundViagem.setPassageiros(viagem.getPassageiros());

        return viagemRepository.save(foundViagem);
    }

    // Atualização parcial
    @PatchMapping("/viagens/{id}")
    public Viagem incrementalUpdateViagem(@PathVariable("id") Long id,
            @RequestBody Viagem viagem) {
        Viagem foundViagem = findViagem(id);

        foundViagem.setOrigem(Optional.ofNullable(viagem.getOrigem())
                .orElse(foundViagem.getOrigem()));

        foundViagem.setDestino(Optional.ofNullable(viagem.getDestino())
                .orElse(foundViagem.getDestino()));

        foundViagem.setDataHora(Optional.ofNullable(viagem.getDataHora())
                .orElse(foundViagem.getDataHora()));

        foundViagem.setPreco(Optional.ofNullable(viagem.getPreco())
                .orElse(foundViagem.getPreco()));

        foundViagem.setMotorista(Optional.ofNullable(viagem.getMotorista())
                .orElse(foundViagem.getMotorista()));

        foundViagem.setPassageiro(Optional.ofNullable(viagem.getPassageiro())
                .orElse(foundViagem.getPassageiro()));
        // Se usar List<Passageiro>:
        // foundViagem.setPassageiros(Optional.ofNullable(viagem.getPassageiros())
        //         .orElse(foundViagem.getPassageiros()));

        return viagemRepository.save(foundViagem);
    }

    // Deletar viagem
    @DeleteMapping("/viagens/{id}")
    public void deleteViagem(@PathVariable("id") Long id) {
        if (!viagemRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Viagem não encontrada");
        }
        viagemRepository.deleteById(id);
    }

    // Endpoints adicionais úteis:

    // Buscar viagens por motorista
    @GetMapping("/viagens/motorista/{motoristaId}")
    public List<Viagem> findViagensByMotorista(@PathVariable("motoristaId") Long motoristaId) {
        return viagemRepository.findByMotoristaId(motoristaId);
    }

    // Buscar viagens por passageiro (se usar 1 passageiro)
    @GetMapping("/viagens/passageiro/{passageiroId}")
    public List<Viagem> findViagensByPassageiro(@PathVariable("passageiroId") Long passageiroId) {
        return viagemRepository.findByPassageiroId(passageiroId);
    }

    // Buscar viagens por origem
    @GetMapping("/viagens/origem/{origem}")
    public List<Viagem> findViagensByOrigem(@PathVariable("origem") String origem) {
        return viagemRepository.findByOrigem(origem);
    }

    // Buscar viagens por destino
    @GetMapping("/viagens/destino/{destino}")
    public List<Viagem> findViagensByDestino(@PathVariable("destino") String destino) {
        return viagemRepository.findByDestino(destino);
    }
}