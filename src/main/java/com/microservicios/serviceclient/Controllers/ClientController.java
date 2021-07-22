package com.microservicios.serviceclient.Controllers;

import com.microservicios.serviceclient.Entities.Client;
import com.microservicios.serviceclient.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<List<Client>>clients() {
        List<Client> clients=service.clients();
        if (clients.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(clients);
    }
}
