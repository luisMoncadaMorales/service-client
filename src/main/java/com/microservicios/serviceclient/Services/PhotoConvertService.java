package com.microservicios.serviceclient.Services;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.DTO.PhotoDTO;
import com.microservicios.serviceclient.Entities.Client;

import java.util.List;

public interface PhotoConvertService {
    PhotoDTO clientToPhoto(ClientDTO clientDTO);
    ClientDTO photoToClient(ClientDTO clientDTO,PhotoDTO photoDTO);
    List<ClientDTO> photosToClients(List<ClientDTO> clientsDTO, List<PhotoDTO> photosDTO);
}