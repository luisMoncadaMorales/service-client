package com.microservicios.serviceclient.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoDTO {
    private int number_id;
    private String type_id;
    private String image;
}
