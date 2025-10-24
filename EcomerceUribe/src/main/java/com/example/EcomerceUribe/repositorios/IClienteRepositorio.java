package com.example.EcomerceUribe.repositorios;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.ayudas.Departamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClienteRepositorio extends JpaRepository<Cliente, Integer> {

    //GUARDAR
    //EDITAR POR ID
    //ELIMINAR POR ID
    //BUSCAR POR ID
    //BUSCAR TODOS LOS REGISTROS

    //SECCION DE CONSULTAS O QUERIES PERSONALIZADAS
    List<Cliente> findByDepartamento(Departamentos departamento);
    List<Cliente> findByCiudad(String ciudad);
}
