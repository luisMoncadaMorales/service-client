package com.microservicios.serviceclient.Services;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.DTO.ClientRepositoryDTO;
import com.microservicios.serviceclient.DTO.PhotoDTO;
import com.microservicios.serviceclient.Entities.Client;

import java.util.List;

public interface ClientConvertService {
    ClientDTO toClientDTO(ClientRepositoryDTO clientRepositoryDTO,PhotoDTO photoDTO);
    ClientRepositoryDTO toClientRepositoryDTO(ClientDTO clientDTO,PhotoDTO photoDTO);
    List<ClientDTO> toClientsDTO(List<ClientRepositoryDTO> clientsRepositoryDTO, List<PhotoDTO> photosDTO);
    ClientDTO clientRepositoryToDTO(ClientRepositoryDTO clientRepositoryDTO);
    ClientRepositoryDTO DTOToClientRepository(ClientDTO clientDTO);
}
