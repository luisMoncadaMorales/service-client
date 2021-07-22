package com.microservicios.serviceclient.ServiceTests;

import com.microservicios.serviceclient.Entities.Client;
import com.microservicios.serviceclient.Persistence.ClientRepository;
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
        Mockito.when(repository.clients()).thenReturn(clients);
    }
    @Test
    public void clientsTest(){
        List<Client> clients=service.clients();
        Assertions.assertThat(clients.size()).isEqualTo(1);
    }
}
