package com.microservicios.serviceclient.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDTO {
    @Getter @Setter private int number_id;
    @Getter @Setter private String type_id;
    @Getter @Setter private String name;
    @Getter @Setter private String last_name;
    @Getter @Setter private int age;
    @Getter @Setter private String city;
    @Getter @Setter private String photo;

}
