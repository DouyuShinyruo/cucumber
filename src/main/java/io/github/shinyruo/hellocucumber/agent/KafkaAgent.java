package io.github.shinyruo.hellocucumber.agent;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;

import java.util.Properties;

@Log4j2
public class KafkaAgent extends AbstractAgent implements MessagingAgent {

    private final String topic;
    private final Properties producerConfig;
    private final Producer<String, String> producer;
    private final Properties consumerConfig;
    private final Consumer<String, String> consumer;

    private final ConsumerRecords<String, String> consumerRecord;

    private final Long DEFAUTL_POLL_DURATION = 1000L;

    public KafkaAgent(String agentName, Properties properties) {
        super(agentName, properties);
        this.topic = properties.getProperty("topic");
        this.producerConfig = setUpProducerConfig(properties);
        this.producer = new KafkaProducer<>(producerConfig);
        this.consumerConfig = setUpConsumerConfig(properties);
        this.consumer = new KafkaConsumer<>(consumerConfig);
        this.consumerRecord = consumer.poll(Duration.ofMillis(DEFAUTL_POLL_DURATION));
    }

    private Properties setUpProducerConfig(Properties properties) {
        final Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getProperty("bootstrap.servers"));
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, properties.getProperty("key.serializer"));
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, properties.getProperty("value.serializer"));
        return producerProperties;
    }

    private Properties setUpConsumerConfig(Properties properties) {
        final Properties consumerProperties = new Properties();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getProperty("bootstrap.servers"));
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, properties.getProperty("key.deserializer"));
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, properties.getProperty("value.deserializer"));
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, properties.getProperty("group.id"));
        return consumerProperties;
    }

    @Override
    public void sendMessage(String message) {
        try {
            producer.send(new ProducerRecord<>(topic, message));
        } catch (Exception e) {
            log.error("Error sending message to Kafka", e);
        }
    }

    @Override
    public String receiveMessage() {
        return consumerRecord.iterator().next().value();
    }
}
