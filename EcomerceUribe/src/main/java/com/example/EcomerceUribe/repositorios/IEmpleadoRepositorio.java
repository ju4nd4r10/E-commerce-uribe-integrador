package com.example.EcomerceUribe.repositorios;

import com.example.EcomerceUribe.modelos.Empleado;
import com.example.EcomerceUribe.ayudas.Cargos;
import com.example.EcomerceUribe.ayudas.Sedes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmpleadoRepositorio extends JpaRepository<Empleado, Integer> {

    //GUARDAR
    //EDITAR POR ID
    //ELIMINAR POR ID
    //BUSCAR POR ID
    //BUSCAR TODOS LOS REGISTROS

    //SECCION DE CONSULTAS O QUERIES PERSONALIZADAS
    List<Empleado> findByCargo(Cargos cargo);
    List<Empleado> findBySede(Sedes sede);
}
