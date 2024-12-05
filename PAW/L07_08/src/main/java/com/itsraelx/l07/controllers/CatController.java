package com.itsraelx.l07.controllers;

import com.itsraelx.l07.models.Cat;
import com.itsraelx.l07.models.ErrorResponse;
import com.itsraelx.l07.repositories.CatRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
class CatController {
    private final CatRepository repository;

    CatController(CatRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cats")
    List<Cat> getCats() {
        return repository.findAll();
    }

    @GetMapping("/cats/{id}")
    ResponseEntity<Object> getCat(@PathVariable("id") Long id) {
        return repository.findById(id)
                .map(cat -> ResponseEntity.ok().body((Object)cat))
                .orElse(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("Cat with ID " + id + " not found")));
    }

    @GetMapping("/fun-fact")
    String getFunFact() {
        final String uri = "https://catfact.ninja/fact";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

    @PostMapping("/cats")
    ResponseEntity<Object> newCat(@RequestBody Cat newCat) {
        if (newCat.getName() == null || newCat.getName().trim().isEmpty() ||
                newCat.getBreed() == null || newCat.getBreed().trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Name and breed cannot be empty"));
        }
        return ResponseEntity.ok().body(repository.save(newCat));
    }

    @PutMapping("/cats/{id}")
    ResponseEntity<Object> replaceCat(@RequestBody Cat newCat, @PathVariable("id") Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse("Cannot update non-existent cat with ID " + id));
        }

        Cat updatedCat = repository.findById(id)
                .map(cat -> {
                    cat.setName(newCat.getName());
                    cat.setBreed(newCat.getBreed());
                    return repository.save(cat);
                })
                .orElseGet(() -> {
                    newCat.setId(id);
                    return repository.save(newCat);
                });

        return ResponseEntity.ok().body(updatedCat);
    }

    @DeleteMapping("/cats/{id}")
    void deleteCat(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }
}