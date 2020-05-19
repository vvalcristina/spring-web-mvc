package br.com.digitalinnovationone.springwebmvc.service;

import br.com.digitalinnovationone.springwebmvc.exception.JediNotFoundException;
import br.com.digitalinnovationone.springwebmvc.model.Jedi;
import br.com.digitalinnovationone.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JediService {

    @Autowired
    JediRepository repository;

    public List<Jedi> findAll() {
        return repository.findAll();
    }

    public Jedi findById(final Long id) {
        final Optional<Jedi> jedi = repository.findById(id);

        if (jedi.isPresent()) {
            return jedi.get();
        } else {
            throw new JediNotFoundException();
        }
    }

    public Jedi save(Jedi jedi) {
        return repository.save(jedi);
    }

    public Jedi update(Long id, Jedi dto) {
        final Optional<Jedi> jediEntity = repository.findById(id);
        final Jedi jedi ;

        if(jediEntity.isPresent()){
            jedi = jediEntity.get();
        }else {
            throw new JediNotFoundException();
        }
        jedi.setName(dto.getName());
        jedi.setLastName(dto.getLastName());

        return jedi;
    }

    public void delete(Long id) {
        final Jedi jedi =findById(id);
        repository.delete(jedi);

    }
}
