package com.microservicios.serviceclient.ServiceTests;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.Entities.Client;
import com.microservicios.serviceclient.Persistence.ClientRepository;
import com.microservicios.serviceclient.Services.ClientDTOService;
import com.microservicios.serviceclient.Services.ClientDTOServiceImp;
import com.microservicios.serviceclient.Services.ClientServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
@SpringBootTest
public class ClientServiceTest {
    @InjectMocks
    private ClientServiceImp service;

    @Mock
    private ClientRepository repository;

    @Mock
    private ClientDTOServiceImp clientDTOServiceImp;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        Client client=Client.builder()
                .number_id(1052)
                .type_id("cc")
                .name("miguel")
                .last_name("moncada")
                .age(27)
                .city("Envigado").build();
        List<Client> clients = Arrays.asList(client);
        ClientDTO clientDTO=ClientDTO.builder()
                .number_id(1052)
                .type_id("cc")
                .name("miguel")
                .last_name("moncada")
                .age(27)
                .city("Envigado")
                .photo("vacio").build();
        List<ClientDTO> clientsDto = Arrays.asList(clientDTO);
        Mockito.when(repository.clients(1)).thenReturn(clients);
        Mockito.when(clientDTOServiceImp.listClientToDTO(clients)).thenReturn(clientsDto);
    }
    @Test
    public void clientsTest(){
        List<ClientDTO> clients=service.clients(1);
        Assertions.assertThat(clients.size()).isEqualTo(1);
    }
}
