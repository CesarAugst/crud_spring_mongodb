package br.edu.fit.datapersistence.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fit.datapersistence.models.Person;
import br.edu.fit.datapersistence.repositories.PersonRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping
public class PersonController {
    @Autowired
    private PersonRepository repository;

    //get - all
    @GetMapping("/")
    public List<Person> all() {
        return repository.findAll();
    }
    //get - find
    @GetMapping("/{id}")
    public Person find(@PathVariable("id") UUID id) {
        return repository.findById(id).orElseThrow();
    }
    //post - criação
    @PostMapping("/")
    public Person create(@RequestBody Person person){
        person.setId(UUID.randomUUID());
        repository.save(person);
        return person;
    }
    //put - update
    @PutMapping("/{id}")
    public Person update(@PathVariable("id") UUID id, @RequestBody Person person){
        var p = repository.findById(id);
        if(p.isPresent()){
            var newPerson = p.get();
            newPerson.setName(person.getName());
            repository.save(newPerson);
        }
        return null;
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id){
        repository.delete(repository.findById(id).orElseThrow());
    }

    
}
