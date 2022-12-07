package br.edu.fit.datapersistence.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.fit.datapersistence.models.Person;

public interface PersonRepository extends MongoRepository<Person, UUID>{
    
}
