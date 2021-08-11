package com.microservicios.serviceclient.Services;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.DTO.PhotoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoConvertServiceImp implements PhotoConvertService {
    @Override
    public PhotoDTO clientToPhoto(ClientDTO clientDTO,String idPhoto) {
        PhotoDTO photoDTO=null;
        String id=null;
        if(idPhoto!=null){
            id=idPhoto;
        }
        photoDTO=PhotoDTO.builder()
                .id(id)
                .image(clientDTO.getPhoto())
                .build();
        return photoDTO;
    }

    @Override
    public ClientDTO photoToClient(ClientDTO clientDTO, PhotoDTO photoDTO,boolean save) {
        if(save){
            if (photoDTO!=null){
                clientDTO.setPhoto(photoDTO.getId());
            }
        }else{
            if (photoDTO!=null){
                clientDTO.setPhoto(photoDTO.getImage());
            }else{
                clientDTO.setPhoto(null);
            }
        }

        return clientDTO;
    }

    @Override
    public List<ClientDTO> photosToClients(List<ClientDTO> clientsDTO, List<PhotoDTO> photosDTO) {
        List<ClientDTO> clientsDTOResult=new ArrayList<>();
        for (ClientDTO clientDTO:clientsDTO) {
            String image=photosDTO.stream()
                    .filter(photo -> photo.getId().toString().equals(clientDTO.getPhoto()))
                    .map(photo-> photo.getImage())
                    .findFirst()
                    .orElse(null);
           if(image!=null){
               clientDTO.setPhoto(image);
           }else{
               clientDTO.setPhoto(null);
           }
           clientsDTOResult.add(clientDTO);
        }
        return clientsDTOResult;
    }
}
