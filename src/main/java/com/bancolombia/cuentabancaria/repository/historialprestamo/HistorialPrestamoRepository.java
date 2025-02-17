package com.bancolombia.cuentabancaria.repository.historialprestamo;

import com.bancolombia.cuentabancaria.model.entity.historialprestamo.HistorialPrestamoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistorialPrestamoRepository extends JpaRepository<HistorialPrestamoEntity, Long> {

    @Query(value = "SELECT * FROM historial_prestamo WHERE id_prestamo = :idPrestamo", nativeQuery = true)
    HistorialPrestamoEntity findHistorialPrestamoByIdPrestamo(@Param("idPrestamo") Long idPrestamo);

    @Query(value = "SELECT hpre.* FROM historial_prestamo hpre " +
            "INNER JOIN prestamo pre " +
            "ON hpre.id_prestamo = pre.id " +
            "WHERE pre.id_cliente = :IdCliente " +
            "LIMIT 3", nativeQuery = true)
    List<HistorialPrestamoEntity> findHistorialPrestamoByIdClienteLast3(@Param("IdCliente") Long IdCliente);

    @Query(value = "SELECT hpre.* FROM historial_prestamo hpre " +
            "INNER JOIN prestamo pre " +
            "ON hpre.id_prestamo = pre.id " +
            "WHERE pre.id_cliente = :IdCliente", nativeQuery = true)
    List<HistorialPrestamoEntity> findHistorialPrestamoByIdCliente(@Param("IdCliente") Long IdCliente);
}
