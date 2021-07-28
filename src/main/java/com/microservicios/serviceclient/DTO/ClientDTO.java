package com.microservicios.serviceclient.DTO;

import lombok.*;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ClientDTO {


    @Positive(message = "the number_id must be greater than 0")
    private int number_id;

    @NotEmpty(message = "the type_id field cannot be empty")
    private String type_id;

    @NotEmpty(message = "the name field cannot be empty")
    private String name;

    @NotEmpty(message = "the last_name field cannot be empty")
    private String last_name;

    @Positive(message = "the age must be greater than 0")
    private int age;

    @NotEmpty(message = "the city field cannot be empty")
    private String city;

    private String photo;

}
