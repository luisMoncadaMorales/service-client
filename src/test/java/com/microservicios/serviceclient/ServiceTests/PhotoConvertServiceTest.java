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

@SpringBootTest
public class PhotoConvertServiceTest {
    @InjectMocks
    PhotoConvertServiceImp photoConvertService;

    private ClientDTO clientDTO;
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        clientDTO=ClientDTO.builder()
                .number_id(1052)
                .type_id("cc")
                .name("miguel")
                .last_name("moncada")
                .age(27)
                .city("Envigado")
                .photo("photo1052").build();
    }

    @Test
    public void clientToPhotoTest(){
        PhotoDTO result=photoConvertService.clientToPhoto(clientDTO,"6111dbaa95514d59d84fd212");
        Assertions.assertThat(result.getId()).isEqualTo("6111dbaa95514d59d84fd212");
    }
}
