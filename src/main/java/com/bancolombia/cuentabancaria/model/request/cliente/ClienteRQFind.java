package com.bancolombia.cuentabancaria.model.request.cliente;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ClienteRQFind {

    @NotNull(message = "El valor no puede ser nulo")
    @Positive(message = "El valor debe ser positivo")
    private Long id;

    public ClienteRQFind(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteRQFind() {
    }
}
