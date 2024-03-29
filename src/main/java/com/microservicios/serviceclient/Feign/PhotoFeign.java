package com.microservicios.serviceclient.Feign;

import com.microservicios.serviceclient.DTO.PhotoDTO;
import com.microservicios.serviceclient.Entities.ClientPK;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(name = "ServicePhotoApplication",url = "http://localhost:8081/apiPhoto",fallback = PhotoHystrix.class)
@FeignClient(name = "service-photo", path = "/apiPhoto",fallback = PhotoHystrix.class)
public interface PhotoFeign {
    @PostMapping(value = "savePhoto")
    public ResponseEntity<PhotoDTO> savePhoto(@RequestBody PhotoDTO photo);
    @PostMapping(value = "photosById")
    public ResponseEntity<List<PhotoDTO>> photosById(@RequestBody List<String> clientsDTO);
    @GetMapping(value = "photoById")
    public ResponseEntity<PhotoDTO> photoById(@RequestParam(value = "id") String id);
    @DeleteMapping(value = "deleteById")
    public ResponseEntity<String> deleteById(@RequestParam(value = "id") String id);

}
