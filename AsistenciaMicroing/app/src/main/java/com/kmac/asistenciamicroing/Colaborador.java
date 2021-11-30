package com.kmac.asistenciamicroing;

import java.util.Date;

public class Colaborador {

    private  String uid;
    private String identificacion;
    private String nombre;
    private String cargo;
    private Date fechaIng;
    private Date fechaRet;
    private Date fechaVale;
    private Integer cantidad;
    private String estado;





    public Colaborador() {
    }

    public Colaborador(String id, String nombreColaborador) {
        this.identificacion = id;
        this.nombre= nombreColaborador;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getFechaIng() {
        return fechaIng;
    }

    public void setFechaIng(Date fechaIng) {
        this.fechaIng = fechaIng;
    }

    public Date getFechaRet() {
        return fechaRet;
    }

    public void setFechaRet(Date fechaRet) {
        this.fechaRet = fechaRet;
    }

    public Date getFechaVale() {
        return fechaVale;
    }

    public void setFechaVale(Date fechaVale) {
        this.fechaVale = fechaVale;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombre;
    }




}
