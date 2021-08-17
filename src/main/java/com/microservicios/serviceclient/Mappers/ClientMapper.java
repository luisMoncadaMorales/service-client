package com.microservicios.serviceclient.Mappers;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.DTO.ClientRepositoryDTO;
import com.microservicios.serviceclient.DTO.PhotoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE= Mappers.getMapper(ClientMapper.class);
    @Mapping(expression = "java(null==photoDTO?null:photoDTO.getImage())",target = "photo")
    ClientDTO toClientDTO(ClientRepositoryDTO clientRepositoryDTO, PhotoDTO photoDTO);
    @Mapping(source = "photoDTO.id",target = "id_photo")
    ClientRepositoryDTO toClientRepositoryDTO(ClientDTO clientDTO, PhotoDTO photoDTO);
}
