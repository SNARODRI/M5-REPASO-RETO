package com.bancolombia.cuentabancaria.repository.cliente;

import com.bancolombia.cuentabancaria.model.entity.cliente.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
