package com.microservicios.serviceclient.Persistence;

import com.microservicios.serviceclient.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query(value = "SELECT * FROM clientes WHERE age> ?1 ",nativeQuery = true)
    List<Client> clients(@Param("age") int age);
}
