package com.automaster.msvendedoress.repository;

import com.automaster.msvendedoress.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    boolean existsByRut(String rut);
    Optional<Vendedor> findByRut(String rut);
}