package com.microservicios.serviceclient.RepositoryTests;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.DTO.ClientRepositoryDTO;
import com.microservicios.serviceclient.Entities.Client;
import com.microservicios.serviceclient.Entities.ClientPK;
import com.microservicios.serviceclient.Repository.ClientConvertImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ClientConvertTest {
    @InjectMocks
    private ClientConvertImp service;

    private Client client;
    private List<Client> clients;
    private ClientRepositoryDTO clientRepositoryDTO;
    private List<ClientRepositoryDTO> clientsRepositoryDTO;

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
        clientRepositoryDTO=ClientRepositoryDTO.builder()
                .number_id(1052)
                .type_id("cc")
                .name("miguel")
                .last_name("moncada")
                .age(27)
                .city("Envigado")
                .id_photo("6111dbaa95514d59d84fd212").build();
        clientsRepositoryDTO = Arrays.asList(clientRepositoryDTO);
        clients = Arrays.asList(client);
    }

    @Test
    public void  DTOToClientTest() {
        Client clientResult=service.DTOToClient(this.clientRepositoryDTO);
        Assertions.assertThat(clientResult).isNotNull();
    }
    @Test
    public void  clientToDTOTest() {
        ClientRepositoryDTO result=service.clientToDTO(this.client);
        Assertions.assertThat(result).isNotNull();
    }
    @Test
    public void  listClientToDTOTest() {
        List<ClientRepositoryDTO> result=service.listClientToDTO(this.clients);
        Assertions.assertThat(result.size()).isEqualTo(1);
    }
    @Test
    public void  listDTOToClientTest() {
        List<Client> clientsResult=service.listDTOToClient(this.clientsRepositoryDTO);
        Assertions.assertThat(clientsResult.size()).isEqualTo(1);
    }
}
