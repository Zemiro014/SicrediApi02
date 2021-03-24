package br.jeronimo.api.sicredi.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import br.jeronimo.api.sicredi.domain.Associate;

@Repository
public interface AssociateRepository extends MongoRepository<Associate, String>{

}
