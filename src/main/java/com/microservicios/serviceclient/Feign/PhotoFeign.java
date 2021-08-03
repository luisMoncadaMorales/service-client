package com.microservicios.serviceclient.Feign;

import com.microservicios.serviceclient.DTO.PhotoDTO;
import com.microservicios.serviceclient.Entities.ClientPK;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ServicePhotoApplication",url = "http://localhost:8081/api",fallback = PhotoHystrix.class)
public interface PhotoFeign {
    @PostMapping(value = "savePhoto")
    public ResponseEntity<PhotoDTO> savePhoto(@RequestBody PhotoDTO photo);
    @PostMapping(value = "photosById")
    public ResponseEntity<List<PhotoDTO>> photosById(@RequestBody List<ClientPK> clientsDTO);
    @GetMapping(value = "photoById")
    public ResponseEntity<PhotoDTO> photoById(@RequestParam(value = "id") int id, @RequestParam(value = "typeId") String typeId);
    @DeleteMapping(value = "deleteById")
    public ResponseEntity<String> deleteById(@RequestParam(value = "id") int id,@RequestParam(value = "typeId") String typeId);

}
