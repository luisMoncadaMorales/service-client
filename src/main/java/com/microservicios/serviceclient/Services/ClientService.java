package com.microservicios.serviceclient.Services;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.Entities.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<ClientDTO> clients(int age);
    ClientDTO clientById(int numberId, String type_id);
}
