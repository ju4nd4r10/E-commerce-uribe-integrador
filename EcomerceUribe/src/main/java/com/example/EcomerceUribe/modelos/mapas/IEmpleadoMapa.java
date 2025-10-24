package com.example.EcomerceUribe.modelos.mapas;

import com.example.EcomerceUribe.modelos.DTOS.EmpleadoLaboralDTO;
import com.example.EcomerceUribe.modelos.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IEmpleadoMapa {

    @Mapping(source = "cargo", target = "cargo")
    @Mapping(source = "salario", target = "salario")
    @Mapping(source = "sede", target = "sede")
    EmpleadoLaboralDTO convertir_empleado_a_empleadolaboraldto(Empleado empleado);

    List<EmpleadoLaboralDTO> convertir_lista_a_lista_empleadolaboraldto(List<Empleado> lista);

    EmpleadoLaboralDTO convertir_empleado_a_empleadodto(Empleado empleadoGuardado);
}
