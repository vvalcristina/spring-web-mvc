package br.com.digitalinnovationone.springwebmvc.rest;

import br.com.digitalinnovationone.springwebmvc.exception.JediNotFoundException;
import br.com.digitalinnovationone.springwebmvc.model.Jedi;
import br.com.digitalinnovationone.springwebmvc.repository.JediRepository;
import br.com.digitalinnovationone.springwebmvc.service.JediService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class JediResource {


    @Autowired
    private JediService service;

    @GetMapping("/api/jedi")
    public List<Jedi> getAllJedi(){

        return service.findAll();
    }

    //Retornando um Jedi Especifico
    @GetMapping("/api/jedi/{id}")
    public ResponseEntity<Jedi>getJedi(@PathVariable("id") Long id ){
        final Jedi jedi = service.findById(id);
        return ResponseEntity.ok(jedi);
    }

    @PostMapping("/api/jedi")
    @ResponseStatus(HttpStatus.CREATED)
    public Jedi createJedi(@Valid @RequestBody Jedi jedi){
        return service.save(jedi);
    }

    @PutMapping("/api/jedi/{id}")
    public ResponseEntity<Jedi> updateJedi(@PathVariable("id") Long id, @Valid @RequestBody Jedi dto){
        final Jedi jedi = service.update(id, dto);
        return ResponseEntity.ok(jedi);
    }

    @DeleteMapping("/api/jedi/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
       service.delete(id);
    }

}
