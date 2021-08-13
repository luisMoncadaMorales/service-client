package com.microservicios.serviceclient.Services;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.DTO.PhotoDTO;
import org.springframework.stereotype.Service;

@Service
public class PhotoConvertServiceImp implements PhotoConvertService{
    @Override
    public PhotoDTO clientToPhoto(ClientDTO clientDTO, String idPhoto) {
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
}
