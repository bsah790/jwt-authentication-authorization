package com.secure.jwtimplementation.controller;

import com.secure.jwtimplementation.entity.Employee;
import com.secure.jwtimplementation.repository.EmployeeRepository;
import com.secure.jwtimplementation.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, KafkaProducerService kafkaProducerService) {
        this.employeeRepository = employeeRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws Exception {
        Employee savedEmployee = employeeRepository.save(employee);
        kafkaProducerService.sendEmployee(savedEmployee);
        return ResponseEntity.ok(savedEmployee);
    }
}
