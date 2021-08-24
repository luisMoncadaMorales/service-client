package com.microservicios.serviceclient.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoDTO {
    private String id;
    private String image;
}
