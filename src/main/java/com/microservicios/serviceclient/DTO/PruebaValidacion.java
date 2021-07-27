package com.microservicios.serviceclient.DTO;

import com.sun.istack.NotNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class PruebaValidacion implements Serializable {
    @NotEmpty(message = "error")
    //@NotBlank(message = "error")
    private String nombre;
    //@NotEmpty(message = "error")
    //@NotBlank(message = "error")
    //@Min(1)
    //@Max(10)
    private int edad;

    public PruebaValidacion() {
    }

    public PruebaValidacion(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
