package com.microservicios.serviceclient.Persistence;

import com.microservicios.serviceclient.Entities.Client;
import com.microservicios.serviceclient.Entities.ClientPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientDAO extends JpaRepository<Client, ClientPK> {
    @Query(value = "SELECT * FROM clientes WHERE age> ?1 ",nativeQuery = true)
    List<Client> clients(@Param("age") int age);
    @Query(value = "SELECT id_photo FROM clientes WHERE number_id=?1 AND type_id=?2",nativeQuery = true)
    String getIdPhoto(@Param("id") int id, @Param("type_id") String type_id);
}
