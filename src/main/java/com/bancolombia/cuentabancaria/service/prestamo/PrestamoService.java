package com.bancolombia.cuentabancaria.service.prestamo;

import com.bancolombia.cuentabancaria.model.entity.cliente.ClienteEntity;
import com.bancolombia.cuentabancaria.model.entity.historialprestamo.HistorialPrestamoEntity;
import com.bancolombia.cuentabancaria.model.entity.prestamo.PrestamoEntity;
import com.bancolombia.cuentabancaria.model.request.prestamo.PrestamoRQ;
import com.bancolombia.cuentabancaria.model.request.prestamo.PrestamoRQFind;
import com.bancolombia.cuentabancaria.repository.cliente.ClienteRepository;
import com.bancolombia.cuentabancaria.repository.historialprestamo.HistorialPrestamoRepository;
import com.bancolombia.cuentabancaria.repository.prestamo.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Service
public class PrestamoService {

    private final PrestamoRepository repository;
    private final ClienteRepository clienteRepository;
    private final HistorialPrestamoRepository historialPrestamoRepository;

    public PrestamoService(PrestamoRepository repository, ClienteRepository clienteRepository, HistorialPrestamoRepository historialPrestamoRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.historialPrestamoRepository = historialPrestamoRepository;
    }

    public boolean validSaldo(BigDecimal valor){
        if(valor.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("El saldo no puede ser negativo");
        }
        return true;
    }

    public PrestamoEntity savePrestamo(PrestamoRQ prestamoRQ){
        PrestamoEntity prestamoEntity = new PrestamoEntity();
        ClienteEntity cliente = clienteRepository.findById(prestamoRQ.getIdCliente()).orElseThrow(()
                -> new NullPointerException("El cliente no existe"));
        if(validSaldo(prestamoRQ.getMonto())){
            prestamoEntity = new PrestamoEntity();
            prestamoEntity.setMonto(prestamoRQ.getMonto());
            prestamoEntity.setInteres(prestamoRQ.getInteres());
            prestamoEntity.setDuracionMeses(prestamoRQ.getDuracionMeses());
            prestamoEntity.setEstado("PENDIENTE");
            prestamoEntity.setFechaCreacion(new Date());
            prestamoEntity.setCliente(cliente);
            prestamoEntity = repository.save(prestamoEntity);

            saveHistorialPrestamo(prestamoEntity);
        }
        return prestamoEntity;
    }

    public void saveHistorialPrestamo(PrestamoEntity prestamo){
        HistorialPrestamoEntity historialPrestamoEntity = new HistorialPrestamoEntity();
        historialPrestamoEntity.setPrestamo(prestamo);
        historialPrestamoEntity.setMonto(prestamo.getMonto());
        historialPrestamoEntity.setEstado(prestamo.getEstado());
        historialPrestamoEntity.setFechaCreacion(new Date());
        historialPrestamoEntity.setFechaActualizacion(new Date());
        historialPrestamoRepository.save(historialPrestamoEntity);
    }

    public PrestamoEntity updatePrestamo(PrestamoRQFind prestamoRQFind){
        PrestamoEntity prestamoEntity = repository.findById(prestamoRQFind.getId()).orElseThrow(()
                -> new NullPointerException("El prestamo no existe"));
        prestamoEntity.setEstado("APROBADO");
        prestamoEntity = repository.save(prestamoEntity);
        updateHistorialPrestamo(prestamoEntity.getId());
        return prestamoEntity;
    }

    public void updateHistorialPrestamo(Long idPrestamo){
        HistorialPrestamoEntity historialPrestamoEntity = historialPrestamoRepository
                .findHistorialPrestamoByIdPrestamo(idPrestamo);
        if(historialPrestamoEntity != null){
            historialPrestamoEntity.setEstado("APROBADO");
            historialPrestamoEntity.setFechaActualizacion(new Date());
            historialPrestamoRepository.save(historialPrestamoEntity);
        }else{
            throw new NullPointerException("El historial del prestamo no existe");
        }
    }

    public PrestamoEntity findPrestamoById(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new NullPointerException("El prestamo no existe"));
    }

    public BigDecimal getSimulacion(Long id){
        PrestamoEntity prestamoEntity = repository.findById(id).orElseThrow(()
                -> new NullPointerException("El prestamo no existe"));
        BigDecimal interesMensual = BigDecimal.valueOf(prestamoEntity.getInteres()).divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);
        BigDecimal monto = prestamoEntity.getMonto();
        BigDecimal montoMensual = monto.divide(BigDecimal.valueOf(prestamoEntity.getDuracionMeses()), 2, RoundingMode.HALF_UP);
        BigDecimal valorInteres= montoMensual.multiply(interesMensual);
        return montoMensual.add(valorInteres.setScale(2, RoundingMode.HALF_UP));
    }
}
