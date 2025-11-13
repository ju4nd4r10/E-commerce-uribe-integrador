package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.EmpleadoLaboralDTO;
import com.example.EcomerceUribe.modelos.DTOS.ProductoResumenDTO;
import com.example.EcomerceUribe.modelos.Empleado;

import com.example.EcomerceUribe.modelos.mapas.IEmpleadoMapa;
import com.example.EcomerceUribe.repositorios.IEmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    // Buscar todos
    public List<EmpleadoLaboralDTO> buscarTodos() {
        return mapa.convertir_lista_a_lista_empleadolaboraldto(repositorio.findAll());
    }

    // Buscar por id
    public EmpleadoLaboralDTO buscarPorId(Integer id) {
        Empleado empleado = repositorio.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el proveedor con id " + id)
        );
        return mapa.convertir_empleado_a_empleadodto(empleado);
    }

    // Eliminar
    public void eliminar(Integer id) {
        Empleado empleado = repositorio.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el proveedor con id " + id)
        );
        repositorio.delete(empleado);
    }

    // Actualizar (nombre y contacto)
    public EmpleadoLaboralDTO actualizar(Integer id, Empleado nuevosDatos) {
        Empleado empleado = repositorio.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el proveedor con id " + id)
        );
        empleado.setCargo(nuevosDatos.getCargo());
        empleado.setSalario(nuevosDatos.getSalario());

        return mapa.convertir_empleado_a_empleadodto(repositorio.save(empleado));
    }

}
