package com.bancolombia.cuentabancaria.controller.prestamo;

import com.bancolombia.cuentabancaria.model.entity.prestamo.PrestamoEntity;
import com.bancolombia.cuentabancaria.model.request.prestamo.PrestamoRQ;
import com.bancolombia.cuentabancaria.model.request.prestamo.PrestamoRQFind;
import com.bancolombia.cuentabancaria.service.historialprestamo.HistorialPrestamoService;
import com.bancolombia.cuentabancaria.service.prestamo.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/prestamo")
public class PrestamoController {

    private final PrestamoService prestamoService;
    private final HistorialPrestamoService historialPrestamoService;

    public PrestamoController(PrestamoService prestamoService, HistorialPrestamoService historialPrestamoService) {
        this.prestamoService = prestamoService;
        this.historialPrestamoService = historialPrestamoService;
    }

    @PostMapping(path = "/solicitar")
    public ResponseEntity<Object> solicitar(@Valid @RequestBody PrestamoRQ prestamoRQ){
        Map<String, Object> message = new HashMap<>();
        prestamoService.savePrestamo(prestamoRQ);
        message.put("message", "Prestamos solicitado exitosamente");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(path = "/aprobacion")
    public ResponseEntity<Object> aprobacion(@Valid @RequestBody PrestamoRQFind prestamoRQFind){
        Map<String, Object> message = new HashMap<>();
        prestamoService.updatePrestamo(prestamoRQFind);
        message.put("message", "Prestamos aprobado exitosamente");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(path = "/consultahistorial")
    public ResponseEntity<Object> consulta(@Valid @RequestBody PrestamoRQFind prestamoRQFind){
        Map<String, Object> message = new HashMap<>();
        PrestamoEntity prestamo = prestamoService.findPrestamoById(prestamoRQFind.getId());
        message.put("prestamo", prestamo);
        message.put("historial", historialPrestamoService.findHistorialPrestamoByIdClienteLast3(prestamo.getCliente().getId()));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(path = "/simulacion")
    public ResponseEntity<Object> simulacion(@Valid @RequestBody PrestamoRQFind prestamoRQFind){
        Map<String, Object> message = new HashMap<>();
        message.put("Cuota mensual", prestamoService.getSimulacion(prestamoRQFind.getId()));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
