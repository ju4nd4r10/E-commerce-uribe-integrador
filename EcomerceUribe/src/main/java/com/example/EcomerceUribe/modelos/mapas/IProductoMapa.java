package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.DTOS.ProductoResumenDTO;
import com.example.EcomerceUribe.modelos.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductoMapa {

    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "precio", target = "precio")
    @Mapping(source = "stock", target = "stock")
    ProductoResumenDTO convertir_producto_a_productodetalledto(Producto producto);

    List<ProductoResumenDTO> convertir_lista_a_lista_productodetalledto(List<Producto> lista);

    ProductoResumenDTO convertir_producto_a_productodto(Producto productoGuardado);
}
