package com.microservicios.serviceclient.Controllers;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.Entities.Client;
import com.microservicios.serviceclient.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api")
public class ClientController {

    private ClientService service;
    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping(value = "clients")
    public ResponseEntity<List<ClientDTO>>clients(@RequestParam(value = "age", defaultValue = "-1") int age) {
        List<ClientDTO> clientsDTO=service.clients(age);
        if (clientsDTO.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(clientsDTO);
    }
    @GetMapping(value = "clientById")
    public ResponseEntity<ClientDTO>clientById(@RequestParam(value = "numberId") int numberId,@RequestParam(value = "typeId") String typeId) {
        ClientDTO clientDTO=service.clientById(numberId,typeId);
        if (clientDTO==null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(clientDTO);
    }
}
