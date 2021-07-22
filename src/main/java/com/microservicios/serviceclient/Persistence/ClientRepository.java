package com.microservicios.serviceclient.Persistence;

import com.microservicios.serviceclient.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query(value = "SELECT * FROM clientes",nativeQuery = true)
    List<Client> clients();
}
