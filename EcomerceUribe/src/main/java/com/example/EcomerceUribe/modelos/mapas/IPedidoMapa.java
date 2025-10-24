package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.DTOS.PedidoResumenDTO;
import com.example.EcomerceUribe.modelos.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IPedidoMapa {

    @Mapping(source = "montoTotal", target = "montoTotal")
    @Mapping(source = "fechaCreacion", target = "fechaCreacion")
    @Mapping(source = "fechaEntrega", target = "fechaEntrega")
    @Mapping(source = "costoEnvio", target = "costoEnvio")
    PedidoResumenDTO convertir_pedido_a_pedidoresumendto(Pedido pedido);

    List<PedidoResumenDTO> convertir_lista_a_lista_pedidoresumendto(List<Pedido> lista);

    PedidoResumenDTO convertir_pedido_a_pedidodto(Pedido pedidoGuardado);
}
