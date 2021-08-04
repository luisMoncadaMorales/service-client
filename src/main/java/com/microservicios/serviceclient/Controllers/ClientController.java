package com.microservicios.serviceclient.Controllers;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.Services.ClientService;
import com.microservicios.serviceclient.Services.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api")
public class ClientController {

    private ClientService service;
    private ErrorService errorService;

    @Autowired
    public ClientController(ClientService service, ErrorService errorService) {
        this.service = service;
        this.errorService = errorService;
    }

    @GetMapping(value = "clients")
    public ResponseEntity<List<ClientDTO>>clients(@RequestParam(value = "age", defaultValue = "-1") int age) {
        List<ClientDTO> clientsDTO=service.clients(age);
        if (clientsDTO.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(clientsDTO);
    }
    @GetMapping(value = "clientById")
    public ResponseEntity<ClientDTO>clientById(@RequestParam(value = "numberId",required = true) int numberId, @RequestParam(value = "typeId",required = true) String typeId) {
        ClientDTO clientDTO=service.clientById(numberId,typeId);
        if (clientDTO==null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(clientDTO);
    }
    @PostMapping(value = "saveClient")
    public ResponseEntity<ClientDTO>saveClient(@Valid @RequestBody ClientDTO clientDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,errorService.formatMessage(bindingResult));
        }else {
            ClientDTO clientDTOResult=service.saveClient(clientDTO);
            if (clientDTOResult==null)
                return ResponseEntity.internalServerError().build();
            return ResponseEntity.ok(clientDTOResult);
        }
    }
    @DeleteMapping(value = "deleteClient")
    public ResponseEntity<String>deleteClient(@RequestParam(value = "numberId",required = true) int numberId, @RequestParam(value = "typeId",required = true) String typeId) {
        boolean result=service.deleteClient(numberId,typeId);
        if (!result)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Client delete");
    }

}
