package br.jeronimo.api.sicredi.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jeronimo.api.sicredi.domain.VotingSession;
import br.jeronimo.api.sicredi.domain.util.VoteRequest;
import br.jeronimo.api.sicredi.services.VotingSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value="/votingSessions")
@Api(value="API REST Voting Session")
@CrossOrigin(origins="*")
@AllArgsConstructor
public class VotingSessionResource {

	private final VotingSessionService votingSessionconsumer;
	
	@GetMapping
	@ApiOperation(value="This method returns all existing Voting Session in mongoDB")
	public ResponseEntity<List<VotingSession>> findAllVotingSession(){
		
		List<VotingSession> list = votingSessionconsumer.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/voteGuideline", method=RequestMethod.POST)
	@ApiOperation(value="This method allows you to vote on a specific agenda registered in the system. "
			+ "It is necessary to inform in your body the values ​​of the fields: guidelineId; associateId and vote. "
			+ " The guidelineId must match a valid guideline. "
			+ " The associateId must correspond to a registered associate and the vote must be only SIM or NAO")
	public ResponseEntity<Void> voteGuideline(@RequestBody VoteRequest objRequest)
	{
		votingSessionconsumer.voteGuideline(objRequest);
		
		return ResponseEntity.noContent().build();
	}
}
