package com.eletronalize.promotehealth.Controller;

import com.eletronalize.promotehealth.Entity.FaixaEtaria;
import com.eletronalize.promotehealth.Repository.FaixaEtariaRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ControllerFaixaEtaria {

    private final FaixaEtariaRepo fRepository;

    public ControllerFaixaEtaria(FaixaEtariaRepo fRepository) {
        this.fRepository = fRepository;
    }

    @GetMapping("/faixasetarias")
    public ResponseEntity<?> findAllFaixaEtaria() {
        try {
            List<FaixaEtaria> lista = fRepository.findAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/faixaetaria/{id}")
    public ResponseEntity<FaixaEtaria> findByIdFaixaEtaria(@PathVariable Long id)  {
        try {
            Optional<FaixaEtaria> unidOptional = fRepository.findById(id);
            if (unidOptional.isPresent()) {
                FaixaEtaria faixaEtariaUnid  = unidOptional.get();
                return new ResponseEntity<>(faixaEtariaUnid, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/faixaetaria/novo")
    public FaixaEtaria newFaixaEtaria(@RequestBody FaixaEtaria newFaixa) {
        return fRepository.save(newFaixa);
    }

    @DeleteMapping("/faixaetaria/remover/{id}")
    public void deleteFaixaEtaria(@PathVariable Long id) {
        fRepository.deleteById(id);
    }
}
