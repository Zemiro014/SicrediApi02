package br.jeronimo.api.sicredi.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.jeronimo.api.sicredi.domain.Associate;
import br.jeronimo.api.sicredi.domain.Guideline;
import br.jeronimo.api.sicredi.domain.enums.VotingValue;
import br.jeronimo.api.sicredi.repositories.AssociateRepository;
import br.jeronimo.api.sicredi.repositories.GuidelineRepository;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class DataBaseInstantiater implements CommandLineRunner {

	private final AssociateRepository associateRepository;
	private final GuidelineRepository guidelineRepository;

	@Override
	public void run(String... args) throws Exception {
		
		associateRepository.deleteAll();
		guidelineRepository.deleteAll();
		
		Associate maria = Associate.builder().id(null).name("Maria Brown").email("maria@gmail.com").build();
		Associate alex = Associate.builder().id(null).name("Alex Green").email("alex@gmail.com").build();
		Associate bob = Associate.builder().id(null).name("Bob Grey").email("bob@gmail.com").build();
		Associate ana = Associate.builder().id(null).name("Ana dos Santos").email("ana@gmail.com").build();
		Associate eduardo = Associate.builder().id(null).name("Eduardo Costa").email("eduardo@gmail.com").build();
		Associate camila = Associate.builder().id(null).name("Camila Brito").email("camila@gmail.com").build();
		Associate paulo = Associate.builder().id(null).name("Paulo Andre").email("cocopaulo@gmail.com").build();
		
		associateRepository.saveAll(Arrays.asList(maria, alex, bob, ana, eduardo, camila, paulo));
		
		Guideline guideline1 = Guideline.builder().id(null).title("Despedir funcionário").description("Para se reduzir os custos da empresa").build();
		Guideline guideline2 = Guideline.builder().id(null).title("Sala de TI").description("Para se aumentar a equipe de TI").build();
		
		guidelineRepository.saveAll(Arrays.asList(guideline1, guideline2));
		
		guideline1.setVotes(VotingValue.SIM.toString());
		guideline1.setVotes(VotingValue.SIM.toString());
		guideline1.setVotes(VotingValue.NAO.toString());
		guideline1.setVotes(VotingValue.SIM.toString());
		guideline2.setVotes(VotingValue.SIM.toString());
		guideline2.setVotes(VotingValue.NAO.toString());
		guideline2.setVotes(VotingValue.SIM.toString());
		
		guidelineRepository.save(guideline1);
		guidelineRepository.save(guideline2);
	}
}
