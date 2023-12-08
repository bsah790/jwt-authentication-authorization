package com.secure.jwtimplementation.serialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class CustomSerializer implements Serializer {

    public void configure(Map map, boolean b) {

    }

    public byte[] serialize(String s, Object o) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(o).getBytes();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return retVal;
    }

    public void close() {

    }
}