package com.cme.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.util.List;

public class ListToJsonConverter implements AttributeConverter<List<String>, String> {

    static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> list) {
        String returnValue = null;
        try {
            if (list != null) {
                returnValue = mapper.writeValueAsString(list);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return returnValue;
    }


    @Override
    public List<String> convertToEntityAttribute(String dbJson) {
        List<String> returnList = null;
        try {
            if (dbJson != null) {
                returnList = mapper.readValue(dbJson, new TypeReference<List<String>>() {
                });
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return returnList;
    }
}

