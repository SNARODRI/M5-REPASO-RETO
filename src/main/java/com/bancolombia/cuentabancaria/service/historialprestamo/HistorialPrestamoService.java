package com.bancolombia.cuentabancaria.service.historialprestamo;

import com.bancolombia.cuentabancaria.model.entity.historialprestamo.HistorialPrestamoEntity;
import com.bancolombia.cuentabancaria.repository.historialprestamo.HistorialPrestamoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialPrestamoService {

    private final HistorialPrestamoRepository repository;

    public HistorialPrestamoService(HistorialPrestamoRepository repository) {
        this.repository = repository;
    }

    public List<HistorialPrestamoEntity> findHistorialPrestamoByIdClienteLast3(Long idCliente){
        List<HistorialPrestamoEntity>  historialPrestamoEntity = repository.findHistorialPrestamoByIdClienteLast3(idCliente);
        if(historialPrestamoEntity != null && !historialPrestamoEntity.isEmpty()){
            return historialPrestamoEntity;
        } else {
            throw new NullPointerException("No existe el historial de prestamo por cliente");
        }
    }

    public List<HistorialPrestamoEntity> findHistorialPrestamoByIdCliente(Long idCliente){
        List<HistorialPrestamoEntity>  historialPrestamoEntity = repository.findHistorialPrestamoByIdCliente(idCliente);
        if(historialPrestamoEntity != null && !historialPrestamoEntity.isEmpty()){
            return historialPrestamoEntity;
        } else {
            throw new NullPointerException("No existe el historial de prestamo por cliente");
        }
    }
}
