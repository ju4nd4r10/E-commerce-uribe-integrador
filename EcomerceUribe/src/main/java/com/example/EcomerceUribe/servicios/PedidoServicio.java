package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.PedidoResumenDTO;
import com.example.EcomerceUribe.modelos.DTOS.ProductoResumenDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.mapas.IPedidoMapa;
import com.example.EcomerceUribe.repositorios.IPedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PedidoServicio {

    @Autowired
    private IPedidoRepositorio repositorio;

    @Autowired
    private IPedidoMapa mapa;

    public PedidoResumenDTO guardarPedido(Pedido datosPedido) {
        // Validación: el total no puede ser menor o igual a 0
        if (datosPedido.getTotal() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El valor total del pedido debe ser mayor a 0"
            );
        }

        Pedido pedidoGuardado = this.repositorio.save(datosPedido);
        return this.mapa.convertir_pedido_a_pedidodto(pedidoGuardado);
    }

    // Buscar todos
    public List<PedidoResumenDTO> buscarTodos() {
        return mapa.convertir_lista_a_lista_pedidoresumendto(repositorio.findAll());
    }

    // Buscar por id
    public PedidoResumenDTO buscarPorId(Integer id) {
        Pedido pedido = repositorio.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el pedido con id " + id)
        );
        return mapa.convertir_pedido_a_pedidoresumendto(pedido);
    }

    // Eliminar
    public void eliminar(Integer id) {
        Pedido pedido = repositorio.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el pedido con id " + id)
        );
        repositorio.delete(pedido);
    }

    // Actualizar estado o fecha
    public PedidoResumenDTO actualizar(Integer id, Pedido nuevosDatos) {
        Pedido pedido = repositorio.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el pedido con id " + id)
        );
        pedido.setProductos(nuevosDatos.getProductos());
        pedido.setFechaEntrega(nuevosDatos.getFechaEntrega());

        return mapa.convertir_pedido_a_pedidoresumendto(repositorio.save(pedido));
    }

}
