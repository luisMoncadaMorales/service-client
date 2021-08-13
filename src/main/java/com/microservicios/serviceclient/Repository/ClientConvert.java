package com.microservicios.serviceclient.Repository;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.DTO.ClientRepositoryDTO;
import com.microservicios.serviceclient.Entities.Client;

import java.util.List;

public interface ClientConvert {
    ClientRepositoryDTO clientToDTO(Client client);
    Client DTOToClient(ClientRepositoryDTO clientRepositoryDTO);
    List<ClientRepositoryDTO> listClientToDTO(List<Client> clients);
    List<Client> listDTOToClient(List<ClientRepositoryDTO> clientsRepositoryDTO);
}
