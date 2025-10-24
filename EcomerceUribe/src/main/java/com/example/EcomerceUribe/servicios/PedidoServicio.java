package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.PedidoResumenDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import com.example.EcomerceUribe.modelos.mapas.IPedidoMapa;
import com.example.EcomerceUribe.repositorios.IPedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
}
