package br.jeronimo.api.sicredi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.jeronimo.api.sicredi.domain.VotingSession;

@Repository
public interface VotingSessionRepository extends MongoRepository<VotingSession, String>{
	
	@Query("{$and: [ {'guideline._id': ?0}, {'associate._id': ?1} ] }")
	VotingSession searchExistentVote(String guidelineId, String associateId);

}
