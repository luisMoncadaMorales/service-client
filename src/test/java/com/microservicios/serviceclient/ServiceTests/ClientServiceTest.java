package com.microservicios.serviceclient.ServiceTests;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.Entities.Client;
import com.microservicios.serviceclient.Entities.ClientPK;
import com.microservicios.serviceclient.Repository.ClientRepository;
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

    private ClientPK clientPK;
    private Client client;
    private ClientDTO clientDTO;
    private List<ClientDTO> clientsDto;
    private List<Client> clients;



    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        clientPK= ClientPK.builder()
                .number_id(1052)
                .type_id("cc").build();
        client=Client.builder()
                .clientPk(clientPK)
                .name("miguel")
                .last_name("moncada")
                .age(27)
                .city("Envigado").build();
        clients = Arrays.asList(client);
        clientDTO=ClientDTO.builder()
                .number_id(1052)
                .type_id("cc")
                .name("miguel")
                .last_name("moncada")
                .age(27)
                .city("Envigado")
                .photo("vacio").build();
        clientsDto = Arrays.asList(clientDTO);
        Mockito.when(repository.clients(1)).thenReturn(clientsDto);
        Mockito.when(repository.clientById(1052,"cc")).thenReturn(clientDTO);
        Mockito.when(repository.saveClient(clientDTO)).thenReturn(clientDTO);
        Mockito.when(repository.deleteClient(1052,"cc")).thenReturn(true);
    }
    @Test
    public void clientsTest(){
        List<ClientDTO> clientsResult=service.clients(1);
        Assertions.assertThat(clientsResult.size()).isEqualTo(1);
    }
    @Test
    public void clientByIdTest() {
        ClientDTO clientDTOResult= service.clientById(1052,"cc");
        Assertions.assertThat(clientDTOResult).isNotNull();
    }
    @Test
    public void saveClientTest() {
        ClientDTO clientDTOResult= service.saveClient(this.clientDTO);
        Assertions.assertThat(clientDTOResult).isNotNull();
    }
    @Test
    public void deleteClientTest() {
        boolean result=service.deleteClient(1052,"cc");
        Assertions.assertThat(result).isTrue();
    }
}
