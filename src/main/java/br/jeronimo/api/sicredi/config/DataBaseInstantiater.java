package br.jeronimo.api.sicredi.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.jeronimo.api.sicredi.domain.Associate;
import br.jeronimo.api.sicredi.domain.Guideline;
import br.jeronimo.api.sicredi.domain.VotingSession;
import br.jeronimo.api.sicredi.domain.enums.Perfil;
import br.jeronimo.api.sicredi.domain.enums.VotingValue;
import br.jeronimo.api.sicredi.repositories.AssociateRepository;
import br.jeronimo.api.sicredi.repositories.GuidelineRepository;
import br.jeronimo.api.sicredi.repositories.VotingSessionRepository;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class DataBaseInstantiater implements CommandLineRunner {

	private final AssociateRepository associateRepository;
	private final GuidelineRepository guidelineRepository;
	private final VotingSessionRepository votingSessionRepository;
	private final BCryptPasswordEncoder senhaCodificado;

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		associateRepository.deleteAll();
		guidelineRepository.deleteAll();
		votingSessionRepository.deleteAll();
		
		Associate maria = Associate.builder().id(null).name("Maria Brown").email("maria@gmail.com").cpf("78112325022").senha(senhaCodificado.encode("1234")).build();		
		Associate alex = Associate.builder().id(null).name("Alex Green").email("alex@gmail.com").cpf("22328539009").senha(senhaCodificado.encode("1234")).build();
		Associate bob = Associate.builder().id(null).name("Bob Grey").email("bob@gmail.com").cpf("08377720019").senha(senhaCodificado.encode("1234")).build();
		Associate ana = Associate.builder().id(null).name("Ana dos Santos").email("ana@gmail.com").cpf("55283680061").senha(senhaCodificado.encode("1234")).build();
		Associate eduardo = Associate.builder().id(null).name("Eduardo Costa").email("eduardo@gmail.com").cpf("91404286055").senha(senhaCodificado.encode("1234")).build();
		Associate camila = Associate.builder().id(null).name("Camila Brito").email("camila@gmail.com").cpf("30388160004").senha(senhaCodificado.encode("1234")).build();
		Associate paulo = Associate.builder().id(null).name("Paulo Andre").email("paulo@gmail.com").cpf("82974261060").senha(senhaCodificado.encode("1234")).build();
		
		maria.addPerfil(Perfil.ASSOCIATE);
		alex.addPerfil(Perfil.ASSOCIATE);
		bob.addPerfil(Perfil.ASSOCIATE);
		ana.addPerfil(Perfil.ASSOCIATE);
		eduardo.addPerfil(Perfil.ASSOCIATE);
		camila.addPerfil(Perfil.ASSOCIATE);
		paulo.addPerfil(Perfil.ASSOCIATE);
		paulo.addPerfil(Perfil.ASSOCIATE_ADMIN);
		
		associateRepository.saveAll(Arrays.asList(maria, alex, bob, ana, eduardo, camila, paulo));
		
		Guideline guideline1 = Guideline.builder().id(null).title("Despedir funcionário").description("Para se reduzir os custos da empresa").build();
		Guideline guideline2 = Guideline.builder().id(null).title("Sala de TI").description("Para se aumentar a equipe de TI").build();
		
		guidelineRepository.saveAll(Arrays.asList(guideline1, guideline2));
		
		VotingSession session1 = VotingSession.builder().id(null).dateStart(sdf.parse("13/03/2021 14:19:00")).dateFinish(sdf.parse("13/03/2021 14:21:00")).associate(maria).guideline(guideline1).build();
		VotingSession session2 = VotingSession.builder().id(null).dateStart(sdf.parse("13/03/2021 14:22:00")).dateFinish(sdf.parse("13/03/2021 14:24:00")).associate(alex).guideline(guideline1).build();
		VotingSession session3 = VotingSession.builder().id(null).dateStart(sdf.parse("13/03/2021 14:25:00")).dateFinish(sdf.parse("13/03/2021 14:28:00")).associate(bob).guideline(guideline2).build();
		VotingSession session4 = VotingSession.builder().id(null).dateStart(sdf.parse("13/03/2021 14:29:00")).dateFinish(sdf.parse("13/03/2021 14:31:00")).associate(ana).guideline(guideline2).build();
		VotingSession session5 = VotingSession.builder().id(null).dateStart(sdf.parse("13/03/2021 14:32:00")).dateFinish(sdf.parse("13/03/2021 14:34:00")).associate(eduardo).guideline(guideline1).build();
		VotingSession session6 = VotingSession.builder().id(null).dateStart(sdf.parse("13/03/2021 14:35:00")).dateFinish(sdf.parse("13/03/2021 14:36:00")).associate(camila).guideline(guideline1).build();
		VotingSession session7 = VotingSession.builder().id(null).dateStart(sdf.parse("13/03/2021 14:38:00")).dateFinish(sdf.parse("13/03/2021 14:40:00")).associate(paulo).guideline(guideline1).build();
	
		votingSessionRepository.saveAll(Arrays.asList(session1, session2, session3, session4, session5, session6, session7));
		
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
