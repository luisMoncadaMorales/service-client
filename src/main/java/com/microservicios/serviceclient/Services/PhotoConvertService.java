package com.microservicios.serviceclient.Services;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.DTO.PhotoDTO;

public interface PhotoConvertService {
    PhotoDTO clientToPhoto(ClientDTO clientDTO, String idPhoto);
}
