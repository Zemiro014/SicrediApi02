package br.jeronimo.api.sicredi.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.jeronimo.api.sicredi.domain.Associate;
import br.jeronimo.api.sicredi.domain.Guideline;
import br.jeronimo.api.sicredi.domain.VotingSession;
import br.jeronimo.api.sicredi.domain.util.VoteRequest;
import br.jeronimo.api.sicredi.repositories.GuidelineRepository;
import br.jeronimo.api.sicredi.repositories.VotingSessionRepository;
import br.jeronimo.api.sicredi.services.exception.ObjectNotFoundException;
import br.jeronimo.api.sicredi.services.exception.ObjectNullException;
import br.jeronimo.api.sicredi.services.exception.VotingNotAllowedException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VotingSessionService implements SicrediService<VotingSession, String>{


	private final VotingSessionRepository voteSessionRepository;	
	private final GuidelineRepository guidelineRepository;
	private final SicrediService<Guideline, String> guidelineService;	
	private final SicrediService<Associate, String> associateService;
	
	@Override
	public VotingSession create(VotingSession obj) {
		if(obj==null)
			throw new ObjectNullException("A criação de sessão de voto foi negada porque o objecto passado é null");
		return voteSessionRepository.insert(obj);
	}

	@Override
	public List<VotingSession> findAll() {
		return voteSessionRepository.findAll();
	}

	@Override
	public VotingSession findById(String id) {
		Optional<VotingSession> obj = voteSessionRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("A sessão de voto especificado não existe no nosso sistema"));
	}

	@Override
	public void deleteById(String id) {
		findById(id);
		voteSessionRepository.deleteById(id);	
	}

	@Override
	public VotingSession updateData(VotingSession obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public VotingSession voteGuideline(VoteRequest obj) {
		if(obj==null)
			throw new ObjectNullException("Ação de votar uma pauta foi negada porque o objecto passado é null");
		
		Associate associate = associateService.findById(obj.getAssociateId());
		Guideline guideline = guidelineService.findById(obj.getGuidelineId());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try 
		{	
			VotingSession resul = voteSessionRepository.searchExistentVote(guideline.getId(), associate.getId());
			
			if(resul == null) 
			{				
				if(obj.getVote().equals("SIM") || obj.getVote().equals("NAO")) 
				{
					VotingSession votingSession = new VotingSession(null,sdf.parse("13/03/2021 16:50:00"),sdf.parse("13/03/2021 16:54:00"), associate, guideline);
					voteSessionRepository.insert(votingSession);
					
					guideline.setVotes(obj.getVote());
					guidelineRepository.save(guideline);
					
					return votingSession;
				}
				else 
				{
					throw new VotingNotAllowedException("O valor do voto não é permitido. Escreva SIM ou NAO !!");
				}				
			}
			else 
			{
				throw new VotingNotAllowedException("Não é permitido a um associado votar mais de uma vez em uma mesma pauta !!");
			}
			
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}		
		return null;
	}

}
