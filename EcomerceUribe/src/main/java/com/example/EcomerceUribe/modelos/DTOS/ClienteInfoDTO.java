package com.example.EcomerceUribe.modelos.DTOS;

import com.example.EcomerceUribe.ayudas.Departamentos;

public class ClienteInfoDTO {

    private String direccion;
    private Double calificacion;
    private String referenciaPago;
    private Departamentos departamento;
    private String ciudad;

    public ClienteInfoDTO() {}

    public ClienteInfoDTO(String direccion, Double calificacion, String referenciaPago,
                          Departamentos departamento, String ciudad) {
        this.direccion = direccion;
        this.calificacion = calificacion;
        this.referenciaPago = referenciaPago;
        this.departamento = departamento;
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getReferenciaPago() {
        return referenciaPago;
    }

    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    public Departamentos getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamentos departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
