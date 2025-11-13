package com.example.EcomerceUribe.controladores;

import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.DTOS.ProductoResumenDTO;
import com.example.EcomerceUribe.servicios.ProductoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Controlador para operaciones en la tabla productos")
public class ProductoControlador {

    @Autowired
    private ProductoServicio servicio;

    @Operation(summary = "Crear un producto en la BD")
    @PostMapping(produces = "application/json")
    public ResponseEntity<ProductoResumenDTO> guardar(@RequestBody Producto datos) {
        ProductoResumenDTO respuesta = this.servicio.guardarProducto(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @Operation(summary = "Listar todos los productos guardados en la BD")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ProductoResumenDTO>> listar() {
        List<ProductoResumenDTO> respuesta = this.servicio.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @Operation(summary = "Buscar un producto por su ID")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProductoResumenDTO> buscarPorId(@PathVariable Integer id) {
        ProductoResumenDTO respuesta = this.servicio.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @Operation(summary = "Eliminar un producto en la BD")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Modificar los datos de un producto en la BD")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProductoResumenDTO> modificar(@PathVariable Integer id, @RequestBody Producto datos) {
        ProductoResumenDTO respuesta = this.servicio.actualizar(id, datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}
