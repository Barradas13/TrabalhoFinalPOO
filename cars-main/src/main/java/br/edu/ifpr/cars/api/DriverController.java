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

import br.edu.ifpr.cars.domain.Driver;
import br.edu.ifpr.cars.domain.DriverRepository;
import jakarta.validation.Valid;

@Service
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class DriverController {

    @Autowired // injeção de dependência
    DriverRepository driverRepository; // objeto instancia do repositorio

    @GetMapping("/drivers")
    public List<Driver> listDrivers() {
        return driverRepository.findAll();
    }

    // definir uma Exception personalizada
    @GetMapping("/drivers/{id}")
    public Driver findDriver(@PathVariable("id") Long id) {
        return driverRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/drivers")
    public Driver createDriver(@RequestBody @Valid Driver driver) {
        return driverRepository.save(driver);
    }

    // update
    @PutMapping("/drivers/{id}")
    public Driver fullUpdateDriver(@PathVariable("id") Long id,
            @RequestBody Driver driver) {
        Driver foundDriver = findDriver(id);
        foundDriver.setName(driver.getName());
        foundDriver.setBirthDate(driver.getBirthDate());
        foundDriver.setCNH(driver.getCNH());
        foundDriver.setAnoFabricacao(driver.getAnoFabricacao());
        foundDriver.setComentario(driver.getComentario());
        foundDriver.setPlaca(driver.getPlaca());
        foundDriver.setNumero(driver.getNumero());

        return driverRepository.save(foundDriver);
    }

    @PatchMapping("/drivers/{id}")
    public Driver incrementalUpdateDriver(@PathVariable("id") Long id,
            @RequestBody Driver driver){
            Driver foundDriver = findDriver(id);
            
            foundDriver.setNumero(Optional.ofNullable(driver.getNumero())
            .orElse(foundDriver.getNumero()));

            foundDriver.setName(Optional.ofNullable(driver.getName())
            .orElse(foundDriver.getName()));

            foundDriver.setBirthDate(Optional.ofNullable(driver.getBirthDate())
            .orElse(foundDriver.getBirthDate()));

            foundDriver.setCNH(Optional.ofNullable(driver.getCNH())
            .orElse(foundDriver.getCNH()));
            
            foundDriver.setAnoFabricacao(Optional.ofNullable(driver.getAnoFabricacao())
            .orElse(foundDriver.getAnoFabricacao()));

            foundDriver.setComentario(Optional.ofNullable(driver.getComentario())
            .orElse(foundDriver.getComentario()));

            foundDriver.setPlaca(Optional.ofNullable(driver.getPlaca())
            .orElse(foundDriver.getPlaca()));

            return driverRepository.save(foundDriver);
    }

    @DeleteMapping("/drivers/{id}")
    public void deleteDriver(@PathVariable("id") Long id){
        driverRepository.deleteById(id);
    }


}
