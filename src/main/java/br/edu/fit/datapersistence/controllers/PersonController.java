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

    @GetMapping("/")
    public List<Person> all() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Person find(@PathVariable("id") UUID id) {
        
        return repository.findById(id).orElseThrow();
    }
    
    @PostMapping("/")
    public Person create(@RequestBody Person person){
        person.setId(UUID.randomUUID());
        repository.save(person);
        return person;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id){
        repository.delete(repository.findById(id).orElseThrow());
    }

    @PutMapping("/{id}")
    public Person update(@PathVariable("id") UUID id, @RequestBody Person person){
        Person personFinded = repository.findById(id).orElseThrow();
        personFinded.setName(person.getName());
        return personFinded;
    }
}
