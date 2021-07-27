package com.microservicios.serviceclient.Services;

import org.springframework.validation.BindingResult;

public interface ErrorService {
    String formatMessage(BindingResult bindingResult);
}
