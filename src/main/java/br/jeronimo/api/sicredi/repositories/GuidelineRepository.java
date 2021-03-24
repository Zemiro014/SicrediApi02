package br.jeronimo.api.sicredi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.jeronimo.api.sicredi.domain.Guideline;

@Repository
public interface GuidelineRepository extends MongoRepository<Guideline, String>{

}
