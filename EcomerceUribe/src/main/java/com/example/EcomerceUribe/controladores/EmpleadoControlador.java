package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.EmpleadoLaboralDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.servicios.EmpleadoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@Tag(name = "Controlador para operaciones en la tabla empleados")
public class EmpleadoControlador {

    @Autowired
    private EmpleadoServicio servicio;

    @Operation(summary = "Crear un empleado en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<EmpleadoLaboralDTO> guardar(@RequestBody Empleado datos) {
        EmpleadoLaboralDTO respuesta = this.servicio.guardarEmpleado(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Operation(summary = "Listar todos los empleados guardados en la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<EmpleadoLaboralDTO>> listar() {
        List<EmpleadoLaboralDTO> respuesta = this.servicio.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @Operation(summary = "Buscar un empleado por su ID")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<EmpleadoLaboralDTO> buscarPorId(@PathVariable Integer id) {
        EmpleadoLaboralDTO respuesta = this.servicio.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @Operation(summary = "Eliminar un empleado en la BD")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Modificar un empleado en la BD")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<EmpleadoLaboralDTO> modificar(@PathVariable Integer id, @RequestBody Empleado datos) {
        EmpleadoLaboralDTO respuesta = this.servicio.actualizar(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}
