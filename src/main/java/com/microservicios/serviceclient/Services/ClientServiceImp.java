package com.microservicios.serviceclient.Services;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.Entities.Client;
import com.microservicios.serviceclient.Entities.ClientPK;
import com.microservicios.serviceclient.Persistence.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImp implements ClientService{

    @Autowired
    ClientRepository repository;
    @Autowired
    ClientDTOService clientDTOService;
    @Override
    public List<ClientDTO> clients(int age) {
        List<Client> clients=repository.clients(age);
        return clientDTOService.listClientToDTO(clients);
    }

    @Override
    public ClientDTO clientById(int numberId, String typeId) {
        ClientPK clientPK= ClientPK.builder()
                .number_id(numberId)
                .type_id(typeId).build();
        Optional<Client> client=repository.findById(clientPK);
        if(client.isPresent()) {
            return clientDTOService.clientToDTO(client.get());
        }
        return null;
    }
}
