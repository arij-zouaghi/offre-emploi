package com.iset.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.iset.doa.OffreRepository;
import com.iset.entities.Offre;

import java.util.List;

@RestController
@RequestMapping("/Offres")
public class RestOffres {

    @Autowired
    private OffreRepository offreRepository;

    @GetMapping
    public List<Offre> getAll() {
        return offreRepository.findAll();
    }

    @GetMapping("/{uid}")
    public ResponseEntity<Offre> getById(@PathVariable Long uid) {
        return offreRepository.findById(uid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Offre saveOffre(@RequestBody Offre newOffre) {
        return offreRepository.save(newOffre);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(!offreRepository.existsById(id)) return ResponseEntity.notFound().build();
        offreRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
