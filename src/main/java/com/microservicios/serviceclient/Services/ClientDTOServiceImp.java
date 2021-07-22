package com.microservicios.serviceclient.Services;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.Entities.Client;
import org.hibernate.mapping.Array;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class ClientDTOServiceImp implements ClientDTOService{
    @Override
    public ClientDTO clientToDTO(Client client) {
        ClientDTO clientDTO= ClientDTO.builder()
                .number_id(client.getNumber_id())
                .type_id(client.getType_id())
                .name(client.getName())
                .last_name(client.getLast_name())
                .age(client.getAge())
                .city(client.getCity())
                .photo("vacio")
                .build();
        return clientDTO;
    }

    @Override
    public Client DTOToClient(ClientDTO clientDTO) {
        Client client= Client.builder()
                .number_id(clientDTO.getNumber_id())
                .type_id(clientDTO.getType_id())
                .name(clientDTO.getName())
                .last_name(clientDTO.getLast_name())
                .age(clientDTO.getAge())
                .city(clientDTO.getCity())
                .build();
        return client;
    }

    @Override
    public List<ClientDTO> listClientToDTO(List<Client> clients) {
        ArrayList<ClientDTO> clientsDTO= new ArrayList<>();
        for (Client client: clients) {
            ClientDTO clientDTO=this.clientToDTO(client);
            clientsDTO.add(clientDTO);
        }
        return clientsDTO;
    }

    @Override
    public List<Client> listDTOToClient(List<ClientDTO> clientsDTO) {
        ArrayList<Client> clients= new ArrayList<>();
        for (ClientDTO clientDTO: clientsDTO) {
            clients.add(this.DTOToClient(clientDTO));
        }
        return clients;
    }
}
