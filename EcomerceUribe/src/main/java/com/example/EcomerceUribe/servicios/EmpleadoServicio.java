package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.EmpleadoLaboralDTO;
import com.example.EcomerceUribe.modelos.Empleado;

import com.example.EcomerceUribe.modelos.mapas.IEmpleadoMapa;
import com.example.EcomerceUribe.repositorios.IEmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmpleadoServicio {

    @Autowired
    private IEmpleadoRepositorio repositorio;

    @Autowired
    private IEmpleadoMapa mapa;

    public EmpleadoLaboralDTO guardarEmpleado(Empleado datosEmpleado) {
        // Validación: el correo no puede estar vacío
        if (datosEmpleado.getCorreo() == null || datosEmpleado.getCorreo().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El correo del empleado es obligatorio"
            );
        }

        Empleado empleadoGuardado = this.repositorio.save(datosEmpleado);
        return this.mapa.convertir_empleado_a_empleadodto(empleadoGuardado);
    }
}
