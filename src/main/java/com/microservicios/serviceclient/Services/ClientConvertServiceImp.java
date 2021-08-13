package com.microservicios.serviceclient.Services;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.DTO.ClientRepositoryDTO;
import com.microservicios.serviceclient.DTO.PhotoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientConvertServiceImp implements ClientConvertService {


    @Override
    public ClientDTO toClientDTO(ClientRepositoryDTO clientRepositoryDTO, PhotoDTO photoDTO) {
        ClientDTO clientDTO= this.clientRepositoryToDTO(clientRepositoryDTO);
        if(photoDTO!=null){
            clientDTO.setPhoto(photoDTO.getImage());
        }
        return clientDTO;
    }

    @Override
    public ClientRepositoryDTO toClientRepositoryDTO(ClientDTO clientDTO, PhotoDTO photoDTO) {
        ClientRepositoryDTO clientRepositoryDTO=this.DTOToClientRepository(clientDTO);
        clientRepositoryDTO.setId_photo(photoDTO.getId());
        return clientRepositoryDTO;
    }

    @Override
    public List<ClientDTO> toClientsDTO(List<ClientRepositoryDTO> clientsRepositoryDTO, List<PhotoDTO> photosDTO) {
        List<ClientDTO> clientsDTOResult=new ArrayList<>();
        for (ClientRepositoryDTO clientRepositoryDTO:clientsRepositoryDTO) {
            String image=photosDTO.stream()
                    .filter(photo -> photo.getId().toString().equals(clientRepositoryDTO.getId_photo()))
                    .map(photo-> photo.getImage())
                    .findFirst()
                    .orElse(null);
            ClientDTO clientDTO= this.clientRepositoryToDTO(clientRepositoryDTO);
           if(image!=null){
               clientDTO.setPhoto(image);
           }
           clientsDTOResult.add(clientDTO);
        }
        return clientsDTOResult;
    }

    @Override
    public ClientDTO clientRepositoryToDTO(ClientRepositoryDTO clientRepositoryDTO) {
        ClientDTO clientDTO= ClientDTO.builder()
                .number_id(clientRepositoryDTO.getNumber_id())
                .type_id(clientRepositoryDTO.getType_id())
                .name(clientRepositoryDTO.getName())
                .last_name(clientRepositoryDTO.getLast_name())
                .age(clientRepositoryDTO.getAge())
                .city(clientRepositoryDTO.getCity())
                .photo(null)
                .build();
        return clientDTO;
    }

    @Override
    public ClientRepositoryDTO DTOToClientRepository(ClientDTO clientDTO) {
        ClientRepositoryDTO clientRepositoryDTO= ClientRepositoryDTO.builder()
                .number_id(clientDTO.getNumber_id())
                .type_id(clientDTO.getType_id())
                .name(clientDTO.getName())
                .last_name(clientDTO.getLast_name())
                .age(clientDTO.getAge())
                .city(clientDTO.getCity())
                .id_photo(null)
                .build();
        return clientRepositoryDTO;
    }
}
