package com.microservicios.serviceclient.Services;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.Entities.Client;

import java.util.List;

public interface ClientDTOService {
    ClientDTO clientToDTO(Client client);
    Client DTOToClient(ClientDTO clientDTO);
    List<ClientDTO> listClientToDTO(List<Client> clients);
    List<Client> listDTOToClient(List<ClientDTO> clientsDTO);
}
