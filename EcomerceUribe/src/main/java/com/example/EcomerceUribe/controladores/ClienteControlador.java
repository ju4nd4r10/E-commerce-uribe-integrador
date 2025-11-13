package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteInfoDTO;
import com.example.EcomerceUribe.servicios.ClienteServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Controlador para operaciones en la tabla clientes")
public class ClienteControlador {

    @Autowired
    private ClienteServicio servicio;

    @Operation(summary = "Crear un cliente en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<ClienteInfoDTO> guardar(@RequestBody Cliente datos) {
        ClienteInfoDTO respuesta = this.servicio.guardarCliente(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Operation(summary = "Listar todos los clientes guardados en la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ClienteInfoDTO>> listar() {
        List<ClienteInfoDTO> respuesta = this.servicio.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @Operation(summary = "Buscar un cliente por su ID")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ClienteInfoDTO> buscarPorId(@PathVariable Integer id) {
        ClienteInfoDTO respuesta = this.servicio.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @Operation(summary = "Eliminar un cliente en la BD")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Modificar los datos de un cliente en la BD")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ClienteInfoDTO> modificar(@PathVariable Integer id, @RequestBody Cliente datos) {
        ClienteInfoDTO respuesta = this.servicio.actualizar(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}
