package com.microservicios.serviceclient.ServiceTests;

import com.microservicios.serviceclient.DTO.ClientDTO;
import com.microservicios.serviceclient.DTO.ClientRepositoryDTO;
import com.microservicios.serviceclient.DTO.PhotoDTO;
import com.microservicios.serviceclient.Services.ClientConvertServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ClientConvertServiceTest {

    @InjectMocks
    private ClientConvertServiceImp service;

    private PhotoDTO photoDTO;
    private ClientDTO clientDTO;
    private ClientRepositoryDTO clientRepositoryDTO;
    private List<ClientRepositoryDTO> clientsRepositoryDTO;
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
                .photo("photo1052").build();
        clientRepositoryDTO=clientRepositoryDTO.builder()
                .number_id(1052)
                .type_id("cc")
                .name("miguel")
                .last_name("moncada")
                .age(27)
                .city("Envigado")
                .id_photo("6111dbaa95514d59d84fd212").build();
        clientsRepositoryDTO = Arrays.asList(clientRepositoryDTO);
        clientsDto = Arrays.asList(clientDTO);
        photosDTO = Arrays.asList(photoDTO);
    }

    @Test
    public void toClientDTOTest() {
        ClientDTO result=service.toClientDTO(this.clientRepositoryDTO,this.photoDTO);
        Assertions.assertThat(result.getPhoto()).isEqualTo("photo1052");
    }
    @Test
    public void toClientRepositoryDTOTest() {
        ClientRepositoryDTO result=service.toClientRepositoryDTO(this.clientDTO,this.photoDTO);
        Assertions.assertThat(result.getId_photo()).isEqualTo("6111dbaa95514d59d84fd212");
    }

    @Test
    public void photosToClientsTest() {
        List<ClientDTO> result=service.toClientsDTO(this.clientsRepositoryDTO,this.photosDTO);
        Assertions.assertThat(result.get(0).getPhoto()).isEqualTo(this.photoDTO.getImage());
    }
}
