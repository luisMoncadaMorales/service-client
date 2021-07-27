package com.microservicios.serviceclient.Repository;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.Entities.Client;

import java.util.List;

public interface ClientConvert {
    ClientDTO clientToDTO(Client client);
    Client DTOToClient(ClientDTO clientDTO);
    List<ClientDTO> listClientToDTO(List<Client> clients);
    List<Client> listDTOToClient(List<ClientDTO> clientsDTO);
}
