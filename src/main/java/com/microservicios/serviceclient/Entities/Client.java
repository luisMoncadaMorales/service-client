package com.microservicios.serviceclient.Entities;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name = "clientes")
@Builder
public class Client implements Serializable {
    @Id
    private int number_id;
    private String type_id;
    private String name;
    private String last_name;
    private int age;
    private String city;

    public Client() {
    }

    public Client(int number_id, String type_id, String name, String last_name, int age, String city) {
        this.number_id = number_id;
        this.type_id = type_id;
        this.name = name;
        this.last_name = last_name;
        this.age = age;
        this.city = city;
    }

    public int getNumber_id() {
        return number_id;
    }

    public void setNumber_id(int number_id) {
        this.number_id = number_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
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
