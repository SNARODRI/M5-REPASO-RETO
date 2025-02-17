package com.bancolombia.cuentabancaria.controller.cliente;

import com.bancolombia.cuentabancaria.model.request.cliente.ClienteRQFind;
import com.bancolombia.cuentabancaria.service.historialprestamo.HistorialPrestamoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    private final HistorialPrestamoService historialPrestamoService;

    public ClienteController(HistorialPrestamoService historialPrestamoService) {
        this.historialPrestamoService = historialPrestamoService;
    }

    @PostMapping(path = "/consultahistorial")
    public ResponseEntity<Object> consulta(@Valid @RequestBody ClienteRQFind clienteRQFind){
        return new ResponseEntity<>(historialPrestamoService.findHistorialPrestamoByIdCliente(clienteRQFind.getId()), HttpStatus.OK);
    }
}
