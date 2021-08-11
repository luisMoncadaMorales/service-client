package com.microservicios.serviceclient.PersistenceTests;

import com.microservicios.serviceclient.Entities.Client;
import com.microservicios.serviceclient.Entities.ClientPK;
import com.microservicios.serviceclient.Persistence.ClientDAO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClientDAOTest {
    @Mock
    private ClientDAO repository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        ClientPK clientPK= ClientPK.builder()
                .number_id(1052)
                .type_id("cc").build();
        Client client=Client.builder()
                .clientPk(clientPK)
                .name("miguel")
                .last_name("moncada")
                .age(27)
                .city("Envigado").build();
        List<Client> clients = Arrays.asList(client);
        Mockito.when(repository.clients(1)).thenReturn(clients);
    }
    @Test
    public void clientsTest(){
        List<Client> clients=repository.clients(1);
        Assertions.assertThat(clients.size()).isEqualTo(1);
    }
}
