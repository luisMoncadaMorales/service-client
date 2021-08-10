package com.microservicios.serviceclient.ServiceTests;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.DTO.PhotoDTO;
import com.microservicios.serviceclient.Services.PhotoConvertServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class PhotoConvertServiceTest {

    @InjectMocks
    private PhotoConvertServiceImp service;

    private PhotoDTO photoDTO;
    private ClientDTO clientDTO;
    private List<ClientDTO> clientsDto;
    private List<PhotoDTO> photosDTO;
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        photoDTO=PhotoDTO.builder()
                .id("6111dbaa95514d59d84fd212")
                .image("photo1052")
                .build();
        clientDTO=ClientDTO.builder()
                .number_id(1052)
                .type_id("cc")
                .name("miguel")
                .last_name("moncada")
                .age(27)
                .city("Envigado")
                .photo("6111dbaa95514d59d84fd212").build();
        clientsDto = Arrays.asList(clientDTO);
        photosDTO = Arrays.asList(photoDTO);
    }
    @Test
    public void clientToPhotoTest() {
        PhotoDTO photoDTOResult=service.clientToPhoto(this.clientDTO,"6111dbaa95514d59d84fd212");
        Assertions.assertThat(photoDTOResult.getId()).isEqualTo("6111dbaa95514d59d84fd212");
    }
    @Test
    public void photoToClientTrueTest() {
        ClientDTO result=service.photoToClient(this.clientDTO,this.photoDTO,true);
        Assertions.assertThat(result.getPhoto()).isEqualTo("6111dbaa95514d59d84fd212");
    }
    @Test
    public void photoToClientFalseTest() {
        ClientDTO result=service.photoToClient(this.clientDTO,this.photoDTO,false);
        Assertions.assertThat(result.getPhoto()).isEqualTo("photo1052");
    }
    @Test
    public void photosToClientsTest() {
        List<ClientDTO> result=service.photosToClients(this.clientsDto,this.photosDTO);
        Assertions.assertThat(result.get(0).getPhoto()).isEqualTo(this.photoDTO.getImage());
    }
}
