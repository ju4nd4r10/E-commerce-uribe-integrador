package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.ProductoResumenDTO;
import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.modelos.mapas.IProductoMapa;
import com.example.EcomerceUribe.repositorios.IProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicio {

    @Autowired
    private IProductoRepositorio repositorio;

    @Autowired
    private IProductoMapa mapa;

    public ProductoResumenDTO guardarProducto(Producto datosProducto) {
        // Validación: el nombre no puede estar vacío
        if (datosProducto.getNombre() == null || datosProducto.getNombre().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El nombre del producto es obligatorio"
            );
        }

        Producto productoGuardado = this.repositorio.save(datosProducto);
        return this.mapa.convertir_producto_a_productodto(productoGuardado);
    }

    // Buscar todos
    public List<ProductoResumenDTO> buscarTodos() {
        List<Producto> productos = repositorio.findAll();
        return mapa.convertir_lista_a_lista_productodetalledto(productos);
    }

    // Buscar por ID
    public ProductoResumenDTO buscarPorId(Integer id) {
        Optional<Producto> producto = repositorio.findById(id);
        if (!producto.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el producto con id " + id);
        }
        return mapa.convertir_producto_a_productodto(producto.get());
    }

    // Eliminar
    public void eliminar(Integer id) {
        Optional<Producto> producto = repositorio.findById(id);
        if (!producto.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el producto con id " + id);
        }
        repositorio.delete(producto.get());
    }

    // Actualizar (nombre y precio)
    public ProductoResumenDTO actualizar(Integer id, Producto nuevosDatos) {
        Optional<Producto> producto = repositorio.findById(id);
        if (!producto.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el producto con id " + id);
        }

        Producto productoExistente = producto.get();
        productoExistente.setNombre(nuevosDatos.getNombre());
        productoExistente.setPrecioUnitario(nuevosDatos.getPrecioUnitario());

        Producto actualizado = repositorio.save(productoExistente);
        return mapa.convertir_producto_a_productodetalledto(actualizado);
    }

}
