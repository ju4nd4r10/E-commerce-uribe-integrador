package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.DTOS.PedidoResumenDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.servicios.PedidoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Controlador para operaciones en la tabla pedidos")
public class PedidoControlador {

    @Autowired
    private PedidoServicio servicio;

    @Operation(summary = "Crear un pedido en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<PedidoResumenDTO> guardar(@RequestBody Pedido datos) {
        PedidoResumenDTO respuesta = this.servicio.guardarPedido(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Operation(summary = "Listar todos los pedidos guardados en la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PedidoResumenDTO>> listar() {
        List<PedidoResumenDTO> respuesta = this.servicio.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @Operation(summary = "Buscar un pedido por su ID")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PedidoResumenDTO> buscarPorId(@PathVariable Integer id) {
        PedidoResumenDTO respuesta = this.servicio.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @Operation(summary = "Eliminar un pedido en la BD")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Modificar un pedido en la BD")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PedidoResumenDTO> modificar(@PathVariable Integer id, @RequestBody Pedido datos) {
        PedidoResumenDTO respuesta = this.servicio.actualizar(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}
