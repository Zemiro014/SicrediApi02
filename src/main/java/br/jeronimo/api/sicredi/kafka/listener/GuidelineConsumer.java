package br.jeronimo.api.sicredi.kafka.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.jeronimo.api.sicredi.domain.Guideline;
import br.jeronimo.api.sicredi.domain.util.GuidelineRequest;
import br.jeronimo.api.sicredi.repositories.GuidelineRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GuidelineConsumer {

	private static final Logger logger = LoggerFactory.getLogger(GuidelineConsumer.class);
	
	private final GuidelineRepository guidelineRepository;
	

	@KafkaListener(topics = "KafkaExample", groupId = "groupId")
	public void consume(String message) {
		logger.info(String.format("Consumed message -> %s",message));
	}

	@KafkaListener(topics = "guideline", groupId = "group_json", containerFactory = "guidelineKafkaListenerFactory")
	public void consumeJson(GuidelineRequest obj) {
		logger.info(String.format("Consumed JSON message -> %s",obj));
		Guideline guideline = Guideline.builder()
				.title(obj.getTitle())
				.description(obj.getDescription())
				.build();
		guidelineRepository.insert(guideline);
	}
}
