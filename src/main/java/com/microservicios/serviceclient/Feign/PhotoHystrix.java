package com.microservicios.serviceclient.Feign;

import com.microservicios.serviceclient.DTO.PhotoDTO;
import com.microservicios.serviceclient.Entities.ClientPK;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PhotoHystrix implements PhotoFeign{
    @Override
    public ResponseEntity<PhotoDTO> savePhoto(PhotoDTO photo) {
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<PhotoDTO>> photosById(List<ClientPK> clientsDTO) {
        List<PhotoDTO> result=new ArrayList<PhotoDTO>();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<PhotoDTO> photoById(int id, String typeId) {
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<String> deleteById(int id, String typeId) {
        return ResponseEntity.ok("errorConection");
    }
}
