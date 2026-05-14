package com.automaster.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendedorResponseDTO {
    private Long id;
    private String rut;
    private String nombre;
    private String zona;
}