package com.microservicios.serviceclient.Services;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.Entities.Client;

import java.util.List;

public interface ClientService {
    List<ClientDTO> clients(int age);
}
