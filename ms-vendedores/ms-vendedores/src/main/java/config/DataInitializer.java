package com.automaster.config; // IMPORTANTE: Sin mayúsculas y en la carpeta config

import com.automaster.model.Vendedor; // Coincide con tu carpeta 'model'
import com.automaster.repository.VendedorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(VendedorRepository repository) {
        return args -> {
            log.info("Buscando registros en la base de datos...");

            if (repository.count() == 0) {
                Vendedor v1 = new Vendedor();
                v1.setNombre("Juan Pérez");
                repository.save(v1);

                log.info("¡Éxito! Se ha creado el primer vendedor: {}", v1.getNombre());
            } else {
                log.info("La base de datos ya tiene datos, no se requiere inicialización.");
            }
        };
    }
}