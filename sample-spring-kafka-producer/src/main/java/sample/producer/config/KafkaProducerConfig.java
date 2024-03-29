package sample.producer.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import sample.producer.domain.WorkUnit;
import sample.producer.service.WorkUnitDispatcher;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkUnitDispatcher.class);
    @Autowired
    private KafkaProducerProperties kafkaProducerProperties;

    @Bean
    public ProducerFactory<String, WorkUnit> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(), stringKeySerializer(), workUnitJsonSerializer());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        LOGGER.info(kafkaProducerProperties.getBootstrap());
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerProperties.getBootstrap());
        return props;
    }

    @Bean
    public KafkaTemplate<String, WorkUnit> workUnitsKafkaTemplate() {
        KafkaTemplate<String, WorkUnit> kafkaTemplate =  new KafkaTemplate<>(producerFactory());
        LOGGER.info(kafkaProducerProperties.getTopic());
        kafkaTemplate.setDefaultTopic(kafkaProducerProperties.getTopic());
        return kafkaTemplate;
    }

    @Bean
    public Serializer stringKeySerializer() {
        return new StringSerializer();
    }

    @Bean
    public Serializer workUnitJsonSerializer() {
        return new JsonSerializer();
    }
}
