package com.example.demo.models.beans;
import java.sql.*;
import java.math.BigDecimal;
public class Viaje {
    private int idViaje;
    private BigDecimal precio;
    private Date fechaViaje;
    private Date fechaReserva;
    private BigDecimal costoTotal;
    private BigDecimal precioBoleto;
    private int cantBoletos;
    private int idSeguro;
    private String ciudadOrigen;
    private String ciudadDestino;
    private int idUsuario;
    private Seguro seguro;













    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Date getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(Date fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public BigDecimal getPrecioBoleto() {
        return precioBoleto;
    }

    public void setPrecioBoleto(BigDecimal precioBoleto) {
        this.precioBoleto = precioBoleto;
    }

    public int getCantBoletos() {
        return cantBoletos;
    }

    public void setCantBoletos(int cantBoletos) {
        this.cantBoletos = cantBoletos;
    }

    public int getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(int idSeguro) {
        this.idSeguro = idSeguro;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }
}
