package com.microservicios.serviceclient.ServiceTests;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.Entities.Client;
import com.microservicios.serviceclient.Entities.ClientPK;
import com.microservicios.serviceclient.Services.ClientDTOServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ClientDTOServiceTest {
    @InjectMocks
    private ClientDTOServiceImp service;

    private Client client;
    private List<Client> clients;
    private ClientDTO clientDTO;
    private List<ClientDTO> clientsDto;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        ClientPK clientPK= ClientPK.builder()
                .number_id(1052)
                .type_id("cc").build();
         client=Client.builder()
                 .clientPk(clientPK)
                .name("miguel")
                .last_name("moncada")
                .age(27)
                .city("Envigado").build();
        clientDTO=ClientDTO.builder()
                .number_id(1052)
                .type_id("cc")
                .name("miguel")
                .last_name("moncada")
                .age(27)
                .city("Envigado")
                .photo("vacio").build();
        clientsDto = Arrays.asList(clientDTO);
        clients = Arrays.asList(client);
    }

    @Test
    public void  DTOToClientTest() {
        Client clientResult=service.DTOToClient(this.clientDTO);
        Assertions.assertThat(clientResult).isNotNull();
    }
    @Test
    public void  clientToDTOTest() {
        ClientDTO clientDTOResult=service.clientToDTO(this.client);
        Assertions.assertThat(clientDTOResult).isNotNull();
    }
    @Test
    public void  listClientToDTOTest() {
        List<ClientDTO> clientDTOResult=service.listClientToDTO(this.clients);
        Assertions.assertThat(clientDTOResult.size()).isEqualTo(1);
    }
    @Test
    public void  listDTOToClientTest() {
        List<Client> clientsResult=service.listDTOToClient(this.clientsDto);
        Assertions.assertThat(clientsResult.size()).isEqualTo(1);
    }
}
