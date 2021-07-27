package com.microservicios.serviceclient.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicios.serviceclient.DTO.ErrorMessage;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ErrorServiceImp implements ErrorService{
    @Override
    public String formatMessage(BindingResult bindingResult) {
        List<Map<String,String>> errors=bindingResult.getFieldErrors().stream()
                .map(err->{
                    Map<String,String> error=new HashMap<>();
                    error.put(err.getField(),err.getDefaultMessage());
                    return  error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage=ErrorMessage.builder()
                .statusCode("01")
                .messages(errors)
                .build();
        ObjectMapper objectMapper=new ObjectMapper();
        String jsonString="";
        try {
            jsonString=objectMapper.writeValueAsString(errorMessage);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
