package com.microservicios.serviceclient.Services;

import com.microservicios.serviceclient.Entities.Client;
import com.microservicios.serviceclient.Persistence.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientServiceImp implements ClientService{

    @Autowired
    ClientRepository repository;
    @Override
    public List<Client> clients() {
        return repository.clients();
    }
}
