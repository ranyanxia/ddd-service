package io.yanxia.ddd_service.service;

import java.util.*;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.shaded.com.google.protobuf.Duration;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaService {

    // @Autowired
    private AdminClient adminClient;
    private Producer<String, String> producer;

    public KafkaService() {
        Properties adminProps = new Properties();
        adminProps.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        adminClient = AdminClient.create(adminProps);

        Properties props = new Properties();
        props.put("boostrap.servers", "localhost:9092");
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        producer = new KafkaProducer<>(props);
    }

    public Set<String> listTopics() throws InterruptedException, ExecutionException {
        ListTopicsResult topics = adminClient.listTopics();
        return topics.names().get();
    }

    public void sendMessage(String topic, String message) {
        producer.send(new ProducerRecord<String,String>(topic, message),(metadata, exception) -> {
            if (exception != null) {
                exception.printStackTrace();
                log.error("error sending message", exception);
            } else {
                log.info("message sent to partition {} with offset {}", metadata.partition(), metadata.offset());
            }
        });
        producer.flush();
    }

    public String consumeLatestMessage(String topic) {
        
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "kafka-controller-group");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));
        ConsumerRecords<String, String> records = consumer.poll(10000);
        if ( !records.isEmpty() ) {
            //assume getting the latest message
            Iterator<ConsumerRecord<String, String>> tmp = records.records(topic).iterator();
            if (tmp.hasNext()) {
                ConsumerRecord<String, String> record = tmp.next();
                log.info("message: {}", record.value());

                return record.value();
            } else {
                return "No new messages";
            }
        
            
        } else {
            return "No new messages";
        }
    }

}
