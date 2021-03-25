package br.jeronimo.api.sicredi.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import br.jeronimo.api.sicredi.domain.VotingSession;

@EnableKafka
@Configuration
public class KafkaVotingSessionConfig {

	@Bean
    public ConsumerFactory<String, String> consumerFactoryToVotingSession() {
        Map<String, Object> config = new HashMap();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> votingSessionkafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactoryToVotingSession());
        return factory; 
    }

    @Bean
    public ConsumerFactory<String, VotingSession> votingSessionConsumerFactory() {
        Map<String, Object> config = new HashMap();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(VotingSession.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, VotingSession> votingSessionKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, VotingSession> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(votingSessionConsumerFactory());
        return factory; 
    }
}
