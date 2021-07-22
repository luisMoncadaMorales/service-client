package com.microservicios.serviceclient.Services;

import com.microservicios.serviceclient.Entities.Client;

import java.util.List;

public interface ClientService {
    List<Client> clients();
}
