package com.secure.jwtimplementation.serialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secure.jwtimplementation.entity.Employee;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class CustomDeserializer implements Deserializer {
    @Override
    public void configure(Map configs, boolean isKey) {
    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        Employee emp = null;
        try {
            emp = mapper.readValue(bytes, Employee.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }

    @Override
    public void close() {
    }
}
