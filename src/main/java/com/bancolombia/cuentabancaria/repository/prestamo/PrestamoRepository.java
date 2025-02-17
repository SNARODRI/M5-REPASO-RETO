package com.bancolombia.cuentabancaria.repository.prestamo;

import com.bancolombia.cuentabancaria.model.entity.prestamo.PrestamoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<PrestamoEntity, Long> {
}
