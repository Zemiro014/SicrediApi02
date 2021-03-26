package br.jeronimo.api.sicredi.kafka.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.jeronimo.api.sicredi.domain.Associate;
import br.jeronimo.api.sicredi.domain.util.AssociateRequest;
import br.jeronimo.api.sicredi.services.SicrediService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AssociateConsumer {
	private static final Logger logger = LoggerFactory.getLogger(AssociateConsumer.class);
	
	private final SicrediService<Associate, String> associateService;
	private final BCryptPasswordEncoder senhaCodificado;
	
	@KafkaListener(topics = "KafkaExample", groupId = "groupId")
	public void consume(String message) {
		logger.info(String.format("Consumed message -> %s",message));
	}

	@KafkaListener(topics = "associate", groupId = "groupJson", containerFactory = "associateKafkaListenerFactory")
	public void consumeJson(AssociateRequest objRequest) {
		logger.info(String.format("Consuming new associate in JSON. Data:-> %s",objRequest));
		Associate associate = Associate.builder()
				.name(objRequest.getName())
				.email(objRequest.getEmail())
				.cpf(objRequest.getCpf())
				.senha(senhaCodificado.encode(objRequest.getSenha())).build();
		
		associateService.create(associate);
	}
}
