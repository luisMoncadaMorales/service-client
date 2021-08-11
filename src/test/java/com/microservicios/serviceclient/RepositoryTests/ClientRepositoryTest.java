package com.microservicios.serviceclient.RepositoryTests;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.Entities.Client;
import com.microservicios.serviceclient.Entities.ClientPK;
import com.microservicios.serviceclient.Persistence.ClientDAO;
import com.microservicios.serviceclient.Repository.ClientConvertImp;
import com.microservicios.serviceclient.Repository.ClientRepositoryImp;
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
import java.util.Optional;

@SpringBootTest
public class ClientRepositoryTest {
    @InjectMocks
    private ClientRepositoryImp repository;

    @Mock
    private ClientDAO clientDAO;
    @Mock
    private ClientConvertImp clientConvertImp;

    private ClientPK clientPK;
    private Client client;
    private List<Client> clients;
    private ClientDTO clientDTO;
    private List<ClientDTO> clientsDto;

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
        Mockito.when(clientDAO.clients(1)).thenReturn(clients);
        Mockito.when(clientDAO.findById(clientPK)).thenReturn(Optional.ofNullable(client));
        Mockito.when(clientDAO.save(client)).thenReturn(client);
        Mockito.when(clientDAO.getIdPhoto(1052,"cc")).thenReturn("6111dbaa95514d59d84fd212");
        Mockito.when(clientConvertImp.listClientToDTO(clients)).thenReturn(clientsDto);
        Mockito.when(clientConvertImp.clientToDTO(client)).thenReturn(clientDTO);
        Mockito.when(clientConvertImp.DTOToClient(clientDTO)).thenReturn(client);
    }
    @Test
    public void clientsTest(){
        List<ClientDTO> clientsResult=repository.clients(1);
        Assertions.assertThat(clientsResult.size()).isEqualTo(1);
    }
    @Test
    public void clientByIdTest() {
        ClientDTO clientDTOResult= repository.clientById(1052,"cc");
        Assertions.assertThat(clientDTOResult).isNotNull();
    }
    @Test
    public void saveClientTest() {
        ClientDTO clientDTOResult=repository.saveClient(clientDTO);
        Assertions.assertThat(clientDTOResult).isNotNull();
    }
    @Test
    public void deleteClientTest() {
       boolean result= repository.deleteClient(1051,"cc");
        Assertions.assertThat(result).isEqualTo(true);
    }
    @Test
    public void getIdPhotoTest() {
        String result= repository.getIdPhoto(1052,"cc");
        Assertions.assertThat(result).isEqualTo("6111dbaa95514d59d84fd212");
    }
}
