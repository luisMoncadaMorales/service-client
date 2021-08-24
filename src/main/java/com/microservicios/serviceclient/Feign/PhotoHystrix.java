package com.microservicios.serviceclient.Feign;

import com.microservicios.serviceclient.DTO.PhotoDTO;
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
    public ResponseEntity<List<PhotoDTO>> photosById(List<String> clientsDTO) {
        List<PhotoDTO> result=new ArrayList<PhotoDTO>();
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<PhotoDTO> photoById(String id) {
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<String> deleteById(String id) {
        return ResponseEntity.ok("errorConection");
    }
}
