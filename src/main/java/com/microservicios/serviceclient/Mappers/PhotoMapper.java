package com.microservicios.serviceclient.Mappers;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.DTO.PhotoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhotoMapper {
    PhotoMapper INSTANCE= Mappers.getMapper(PhotoMapper.class);
    @Mapping(source = "idPhoto",target = "id")
    @Mapping(source = "clientDTO.photo",target ="image")
    PhotoDTO clientToPhoto(ClientDTO clientDTO, String idPhoto);
}
