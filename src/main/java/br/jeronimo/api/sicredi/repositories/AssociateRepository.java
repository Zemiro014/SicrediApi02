package br.jeronimo.api.sicredi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.jeronimo.api.sicredi.domain.Associate;

@Repository
public interface AssociateRepository extends MongoRepository<Associate, String>{

	Associate findByEmail(String email);
}
