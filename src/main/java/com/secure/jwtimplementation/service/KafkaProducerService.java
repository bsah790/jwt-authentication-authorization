package com.secure.jwtimplementation.service;

import com.secure.jwtimplementation.entity.Employee;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaProducerService {
    private static final String TOPIC_NAME = "employee-topic";
    private static final int NUM_PARTITIONS = 3;
    private static final short REPLICATION_FACTOR = 1;
    private static final long RETENTION_PERIOD_MS = 86400000L; // 1 day
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendEmployee(Employee employee) throws Exception {
        kafkaTemplate.executeInTransaction(operations -> {
            operations.send(TOPIC_NAME, String.valueOf(employee.getEmpId()), employee);
            return null;
        });

        AdminClient adminClient = AdminClient.create(kafkaTemplate.getProducerFactory().getConfigurationProperties());
        ListTopicsResult listTopics = adminClient.listTopics();
        Set<String> names = listTopics.names().get();
        boolean isTopicExist = names.contains(TOPIC_NAME);
        if(!isTopicExist) {
            NewTopic newTopic = new NewTopic(TOPIC_NAME, NUM_PARTITIONS, REPLICATION_FACTOR);
            Map<String, String> config = new HashMap<>();
            config.put(TopicConfig.RETENTION_MS_CONFIG, String.valueOf(RETENTION_PERIOD_MS));
            newTopic.configs(config);

            try {
                adminClient.createTopics(Collections.singleton(newTopic)).all().get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            } finally {
                adminClient.close();
            }
        }
    }

}
