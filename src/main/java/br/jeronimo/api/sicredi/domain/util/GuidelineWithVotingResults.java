package br.jeronimo.api.sicredi.domain.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.jeronimo.api.sicredi.domain.Guideline;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuidelineWithVotingResults implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String title;
	private String description;	
	private int quantityVotes;
	private int approved;
	private int rejected;
	private String status;
	
	@Builder.Default
	private List<String> votes = new ArrayList<>();
	
	public GuidelineWithVotingResults(Guideline obj) {
		id = obj.getId();
		title = obj.getTitle();
		description = obj.getDescription();
		quantityVotes = obj.getVotes().size();
		votes = obj.getVotes();
		calculatingStateVoting(obj.getVotes());
	}

	
	
	private void calculatingStateVoting(List<String> votes) {
		int sim = 0;
		int nao = 0;
		
		if(votes == null) 
		{
			setStatus("Esta pauta não possui nenhuma votação");
		}
		else 
		{
			for(String vote : votes) 
			{
				if(vote.equals("SIM")) 
				{
					sim += 1;
				}
				else 
				{
					nao += 1;
				}
			}
			if(sim > nao) {
				setStatus("APROVADA");
			}
			else if(sim == nao)
			{
				setStatus("EMPATADA");
			}
			else {
				setStatus("REPROVADA");
			}
		}
		setApproved(sim);
		setRejected(nao);
	}

}
