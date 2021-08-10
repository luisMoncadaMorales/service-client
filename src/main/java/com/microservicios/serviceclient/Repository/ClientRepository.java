package com.microservicios.serviceclient.Repository;

import com.microservicios.serviceclient.DTO.ClientDTO;

import java.util.List;

public interface ClientRepository {
    List<ClientDTO> clients(int age);
    ClientDTO clientById(int numberId, String typeId);
    ClientDTO saveClient(ClientDTO clientDTO);
    boolean deleteClient(int numberId,String typeId);
    String getIdPhoto(int numberId,String typeId);
}
