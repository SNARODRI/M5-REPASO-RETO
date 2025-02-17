package com.bancolombia.cuentabancaria.model.request.prestamo;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class PrestamoRQ {

    private Long id;

    @NotNull(message = "El valor no puede ser nulo")
    @Positive(message = "El valor debe ser positivo")
    private BigDecimal monto;

    @NotNull(message = "El valor no puede ser nulo")
    @Positive(message = "El valor debe ser positivo")
    private Double interes;

    @NotNull(message = "El valor no puede ser nulo")
    @Positive(message = "El valor debe ser positivo")
    private Long duracionMeses;

    private String estado;

    @NotNull(message = "El valor no puede ser nulo")
    private Long idCliente;

    public PrestamoRQ(Long id, BigDecimal monto, Double interes, Long duracionMeses, String estado, Long idCliente) {
        this.id = id;
        this.monto = monto;
        this.interes = interes;
        this.duracionMeses = duracionMeses;
        this.estado = estado;
        this.idCliente = idCliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Double getInteres() {
        return interes;
    }

    public void setInteres(Double interes) {
        this.interes = interes;
    }

    public Long getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(Long duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public PrestamoRQ() {
    }
}
