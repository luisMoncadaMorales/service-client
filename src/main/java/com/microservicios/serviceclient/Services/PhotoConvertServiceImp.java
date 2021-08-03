package com.microservicios.serviceclient.Services;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.DTO.PhotoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoConvertServiceImp implements PhotoConvertService {
    @Override
    public PhotoDTO clientToPhoto(ClientDTO clientDTO) {
        PhotoDTO photoDTO=PhotoDTO.builder()
                .number_id(clientDTO.getNumber_id())
                .type_id(clientDTO.getType_id())
                .image(clientDTO.getPhoto())
                .build();
        return photoDTO;
    }

    @Override
    public ClientDTO photoToClient(ClientDTO clientDTO, PhotoDTO photoDTO) {
        if (photoDTO!=null){
            clientDTO.setPhoto(photoDTO.getImage());
        }
        return clientDTO;
    }

    @Override
    public List<ClientDTO> photosToClients(List<ClientDTO> clientsDTO, List<PhotoDTO> photosDTO) {
        List<ClientDTO> clientsDTOResult=new ArrayList<>();
        for (ClientDTO clientDTO:clientsDTO) {
            String image=photosDTO.stream()
                    .filter(photo -> photo.getNumber_id()==clientDTO.getNumber_id() && photo.getType_id().equals(clientDTO.getType_id()))
                    .map(photo-> photo.getImage())
                    .findFirst()
                    .orElse(null);
           if(image!=null){
               clientDTO.setPhoto(image);
           }
           clientsDTOResult.add(clientDTO);
        }
        return clientsDTOResult;
    }
}
