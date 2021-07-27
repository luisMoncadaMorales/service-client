package com.microservicios.serviceclient.DTO;

import lombok.Builder;

import java.util.List;
import java.util.Map;
@Builder
public class ErrorMessage {
    private String statusCode;
    private List<Map<String,String>> messages;

    public ErrorMessage() {
    }

    public ErrorMessage(String statusCode, List<Map<String, String>> messages) {
        this.statusCode = statusCode;
        this.messages = messages;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<Map<String, String>> getMessages() {
        return messages;
    }

    public void setMessages(List<Map<String, String>> messages) {
        this.messages = messages;
    }
}
