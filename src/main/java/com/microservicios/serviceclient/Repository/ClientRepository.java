package com.microservicios.serviceclient.Repository;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.DTO.ClientRepositoryDTO;

import java.util.List;

public interface ClientRepository {
    List<ClientRepositoryDTO> clients(int age);
    ClientRepositoryDTO clientById(int numberId, String typeId);
    ClientRepositoryDTO saveClient(ClientRepositoryDTO clientRepositoryDTO);
    boolean deleteClient(int numberId,String typeId);
    String getIdPhoto(int numberId,String typeId);
}
