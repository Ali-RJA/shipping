package com.ups.shipping;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
@Configuration
public class ShippingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShippingApplication.class, args);
	}


	@Bean
	public ProducerFactory<String, String> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "pkc-lzvrd.us-west4.gcp.confluent.cloud:9092");
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		configProps.put(ProducerConfig.ACKS_CONFIG, "all");
		configProps.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, "45000");
		configProps.put("security.protocol", "SASL_SSL");
		configProps.put("sasl.mechanism", "PLAIN");
		configProps.put("sasl.jaas.config",
				"org.apache.kafka.common.security.plain.PlainLoginModule required username='SJROTDOKJXRO2SIC' password='hmJ1tzfD2vsimMZ47fvOoGS1V/zfrSR3Qqw6AL1AxHqOppSVpWq9rySgH0m/hr09';");

		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	public KafkaTemplate<String, String> KafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
}