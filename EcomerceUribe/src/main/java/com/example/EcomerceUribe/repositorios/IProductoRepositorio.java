package com.example.EcomerceUribe.repositorios;

import com.example.EcomerceUribe.modelos.Producto;
import com.example.EcomerceUribe.ayudas.CategoriaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepositorio extends JpaRepository<Producto, Integer> {

    //GUARDAR
    //EDITAR POR ID
    //ELIMINAR POR ID
    //BUSCAR POR ID
    //BUSCAR TODOS LOS REGISTROS

    //SECCION DE CONSULTAS O QUERIES PERSONALIZADAS
    List<Producto> findByCategoria(CategoriaProducto categoria);
    List<Producto> findByMarca(String marca);
}
