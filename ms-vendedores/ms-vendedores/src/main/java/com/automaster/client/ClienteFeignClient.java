package com.automaster.client;

import com.automaster.dto.ClienteResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List; // Import indispensable

@FeignClient(name = "ms-clientes", url = "http://localhost:8080/api/clientes")
public interface ClienteFeignClient {
    @GetMapping
    List<ClienteResponseDTO> obtenerTodosLosClientes();
}