package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IClienteMapa {

    @Mapping(source = "direccion", target = "direccion")
    @Mapping(source = "calificacion", target = "calificacion")
    @Mapping(source = "referenciaPago", target = "referenciaPago")
    @Mapping(source = "departamento", target = "departamento")
    @Mapping(source = "ciudad", target = "ciudad")
    ClienteInfoDTO convertir_cliente_a_clienteinfodto(Cliente cliente);

    List<ClienteInfoDTO> convertir_lista_a_lista_clienteinfodto(List<Cliente> lista);

    ClienteInfoDTO convertir_cliente_a_clientedto(Cliente clienteGuardado);
}
