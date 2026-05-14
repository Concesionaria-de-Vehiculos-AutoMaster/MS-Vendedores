package com.automaster.service;

import com.automaster.client.ClienteFeignClient;
import com.automaster.dto.*;
import com.automaster.model.Vendedor;
import com.automaster.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VendedorService {

    private final VendedorRepository vendedorRepository;
    private final ClienteFeignClient clienteFeignClient;

    public VendedorResponseDTO guardarVendedor(VendedorRequestDTO dto) {
        Vendedor v = new Vendedor();
        v.setRut(dto.getRut());
        v.setNombre(dto.getNombre());
        v.setZona(dto.getZona());
        Vendedor guardado = vendedorRepository.save(v);
        return new VendedorResponseDTO(guardado.getId(), guardado.getRut(), guardado.getNombre(), guardado.getZona());
    }

    public List<ClienteResponseDTO> listarClientesDelVendedor() {
        return clienteFeignClient.obtenerTodosLosClientes();
    }
}