package com.secure.jwtimplementation.service;

import com.secure.jwtimplementation.entity.ConsumeMessage;
import com.secure.jwtimplementation.entity.Employee;
import com.secure.jwtimplementation.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private MessageRepository messageRepository;
    private static final String TOPIC_NAME = "employee-topic";

    @KafkaListener(topics = TOPIC_NAME, groupId = "employee-consumer-group")
    public void receiveEmployee(Employee employee) {
        // do something with the received employee record
        ConsumeMessage message = new ConsumeMessage();
        message.setMessage(employee.toString());
        messageRepository.save(message);
        System.out.println("Received employee: " + employee);
    }
}