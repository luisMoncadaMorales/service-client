package com.microservicios.serviceclient.Entities;


import lombok.Builder;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Builder
public class ClientPK implements Serializable {
    private int number_id;
    private String type_id;

    public ClientPK() {
    }

    public ClientPK(int number_id, String type_id) {
        this.number_id = number_id;
        this.type_id = type_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientPK clientPK = (ClientPK) o;
        return number_id == clientPK.number_id && type_id.equals(clientPK.type_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number_id, type_id);
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
}
