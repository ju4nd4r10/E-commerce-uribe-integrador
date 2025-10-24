package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.DTOS.ProductoDetalleDTO;
import com.example.EcomerceUribe.modelos.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductoMapa {

    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "precio", target = "precio")
    @Mapping(source = "stock", target = "stock")
    ProductoDetalleDTO convertir_producto_a_productodetalledto(Producto producto);

    List<ProductoDetalleDTO> convertir_lista_a_lista_productodetalledto(List<Producto> lista);

    ProductoDetalleDTO convertir_producto_a_productodto(Producto productoGuardado);
}
