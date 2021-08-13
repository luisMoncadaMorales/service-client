package com.microservicios.serviceclient.ServiceTests;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.DTO.ClientRepositoryDTO;
import com.microservicios.serviceclient.DTO.PhotoDTO;
import com.microservicios.serviceclient.Entities.Client;
import com.microservicios.serviceclient.Entities.ClientPK;
import com.microservicios.serviceclient.Feign.PhotoFeign;
import com.microservicios.serviceclient.Repository.ClientRepository;
import com.microservicios.serviceclient.Services.ClientServiceImp;
import com.microservicios.serviceclient.Services.ClientConvertServiceImp;
import com.microservicios.serviceclient.Services.PhotoConvertServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
@SpringBootTest
public class ClientServiceTest {
    @InjectMocks
    private ClientServiceImp service;

    @Mock
    private ClientConvertServiceImp clientConvertService;
    @Mock
    private PhotoConvertServiceImp photoConvertService;
    @Mock
    private ClientRepository repository;
    @Mock
    private PhotoFeign photoFeign;

    private ClientPK clientPK;
    private Client client;
    private ClientDTO clientDTO;
    private ClientRepositoryDTO clientRepositoryDTO;
    private PhotoDTO photoDTO;
    private List<ClientDTO> clientsDto;
    private List<ClientRepositoryDTO> clientsRepositoryDTO;
    private List<Client> clients;
    private List<String> pks;
    private List<PhotoDTO> photosDTO;



    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        clientPK= ClientPK.builder()
                .number_id(1052)
                .type_id("cc").build();
        pks=Arrays.asList("6111dbaa95514d59d84fd212");
        client=Client.builder()
                .clientPk(clientPK)
                .name("miguel")
                .last_name("moncada")
                .age(27)
                .city("Envigado")
                .id_photo("6111dbaa95514d59d84fd212").build();
        clients = Arrays.asList(client);
        clientRepositoryDTO=ClientRepositoryDTO.builder()
                .number_id(1052)
                .type_id("cc")
                .name("miguel")
                .last_name("moncada")
                .age(27)
                .city("Envigado")
                .id_photo("6111dbaa95514d59d84fd212").build();
        clientsRepositoryDTO = Arrays.asList(clientRepositoryDTO);
        clientDTO=ClientDTO.builder()
                .number_id(1052)
                .type_id("cc")
                .name("miguel")
                .last_name("moncada")
                .age(27)
                .city("Envigado")
                .photo("photo 1052").build();
        clientsDto = Arrays.asList(clientDTO);
        photoDTO=PhotoDTO.builder()
                .id("6111dbaa95514d59d84fd212")
                .image("photo 1052")
                .build();
        photosDTO=Arrays.asList(photoDTO);
        Mockito.when(repository.clients(1)).thenReturn(clientsRepositoryDTO);
        Mockito.when(repository.clientById(1052,"cc")).thenReturn(clientRepositoryDTO);
        Mockito.when(repository.saveClient(clientRepositoryDTO)).thenReturn(clientRepositoryDTO);
        Mockito.when(repository.deleteClient(1052,"cc")).thenReturn(true);
        Mockito.when(repository.getIdPhoto(1052,"cc")).thenReturn("6111dbaa95514d59d84fd212");
        Mockito.when(photoFeign.photosById(pks)).thenReturn(ResponseEntity.ok(photosDTO));
        Mockito.when(clientConvertService.toClientsDTO(clientsRepositoryDTO,photosDTO)).thenReturn(clientsDto);
        Mockito.when(photoFeign.photoById("6111dbaa95514d59d84fd212")).thenReturn(ResponseEntity.ok(photoDTO));
        Mockito.when(clientConvertService.toClientDTO(clientRepositoryDTO,photoDTO)).thenReturn(clientDTO);
        Mockito.when(clientConvertService.toClientRepositoryDTO(clientDTO,photoDTO)).thenReturn(clientRepositoryDTO);
        Mockito.when(photoFeign.savePhoto(photoDTO)).thenReturn(ResponseEntity.ok(photoDTO));
        Mockito.when(photoFeign.savePhoto(null)).thenReturn(ResponseEntity.ok(null));
        Mockito.when(photoConvertService.clientToPhoto(clientDTO,"6111dbaa95514d59d84fd212")).thenReturn(photoDTO);
        Mockito.when(photoFeign.deleteById("6111dbaa95514d59d84fd212")).thenReturn(ResponseEntity.ok("removed"));
        Mockito.when(photoFeign.deleteById("6111dbaa95514d59d84fd211")).thenReturn(ResponseEntity.ok("not found"));
    }
    @Test
    public void clientsTest(){
        List<ClientDTO> clientsResult=service.clients(1);
        Assertions.assertThat(clientsResult.get(0).getPhoto()).isEqualTo(this.photoDTO.getImage());
    }
    @Test
    public void clientByIdTest() {
        ClientDTO clientDTOResult= service.clientById(1052,"cc");
        Assertions.assertThat(clientDTOResult.getPhoto()).isEqualTo(this.photoDTO.getImage());
    }
    @Test
    public void saveClientTest() {
        ClientDTO clientDTOResult= service.saveClient(this.clientDTO);
        Assertions.assertThat(clientDTOResult.getPhoto()).isEqualTo("photo 1052");
    }
    @Test
    public void saveClientElseTest() {
        ClientDTO clientDTOResult= service.saveClient(ClientDTO.builder().build());
        Assertions.assertThat(clientDTOResult).isNull();
    }
    @Test
    public void deleteClientTest() {
        boolean result=service.deleteClient(1052,"cc");
        Assertions.assertThat(result).isTrue();
    }
    @Test
    public void deleteClientElseTest() {
        boolean result=service.deleteClient(1053,"cc");
        Assertions.assertThat(result).isFalse();
    }
}
