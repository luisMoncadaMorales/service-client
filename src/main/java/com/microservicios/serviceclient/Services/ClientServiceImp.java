package com.microservicios.serviceclient.Services;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImp implements ClientService{

    @Autowired
    ClientRepository repository;
    @Override
    public List<ClientDTO> clients(int age) {
        return repository.clients(age);
    }

    @Override
    public ClientDTO clientById(int numberId, String typeId) {
        return repository.clientById(numberId,typeId);
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        return repository.saveClient(clientDTO);
    }

    @Override
    public boolean deleteClient(int numberId, String typeId) {
        return repository.deleteClient(numberId,typeId);
    }
}
