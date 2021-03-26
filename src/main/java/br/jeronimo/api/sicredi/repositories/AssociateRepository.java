package br.jeronimo.api.sicredi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.jeronimo.api.sicredi.domain.Associate;

@Repository
public interface AssociateRepository extends MongoRepository<Associate, String>{

	@Transactional(readOnly=true)
	Associate findByEmail(String email);
}
