package com.bancolombia.cuentabancaria.model.request.prestamo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PrestamoRQFind {

    @NotNull(message = "El valor no puede ser nulo")
    @Positive(message = "El valor debe ser positivo")
    private Long id;

    public PrestamoRQFind(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PrestamoRQFind() {
    }
}
