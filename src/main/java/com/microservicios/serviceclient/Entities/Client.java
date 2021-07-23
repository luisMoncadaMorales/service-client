package com.microservicios.serviceclient.Entities;

import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "clientes")
@Builder
public class Client implements Serializable {

    @EmbeddedId
    private ClientPK clientPk;
    private String name;
    private String last_name;
    private int age;
    private String city;

    public Client() {
    }

    public Client(ClientPK clientPk, String name, String last_name, int age, String city) {
        this.clientPk = clientPk;
        this.name = name;
        this.last_name = last_name;
        this.age = age;
        this.city = city;
    }

    public ClientPK getClientPk() {
        return clientPk;
    }

    public void setClientPk(ClientPK clientPk) {
        this.clientPk = clientPk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
