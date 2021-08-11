package com.microservicios.serviceclient.FeignTest;

import com.microservicios.serviceclient.DTO.PhotoDTO;
import com.microservicios.serviceclient.Feign.PhotoHystrix;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class PhotoHystrixTest {
    @InjectMocks
    private PhotoHystrix photoHystrix;

    private PhotoDTO photoDTO;
    private List<PhotoDTO> photosDTO;
    private List<String> ids;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        photoDTO= PhotoDTO.builder()
                .id("6111dbaa95514d59d84fd212")
                .image("photo 1052")
                .build();
        photosDTO= Arrays.asList(photoDTO);
        ids=Arrays.asList("6111dbaa95514d59d84fd212");
    }

    @Test
    public void savePhotoTest(){
      PhotoDTO result=photoHystrix.savePhoto(this.photoDTO).getBody();
        Assertions.assertThat(result).isNull();
    }
    @Test
    public void photosByIdTest(){
        List<PhotoDTO> result=photoHystrix.photosById(this.ids).getBody();
        Assertions.assertThat(result.size()).isEqualTo(0);
    }
    @Test
    public void photoByIdTest(){
        PhotoDTO result=photoHystrix.photoById("6111dbaa95514d59d84fd212").getBody();
        Assertions.assertThat(result).isNull();
    }
    @Test
    public void deleteByIdTest(){
        String result=photoHystrix.deleteById("6111dbaa95514d59d84fd212").getBody();
        Assertions.assertThat(result).isEqualTo("errorConection");
    }
}
