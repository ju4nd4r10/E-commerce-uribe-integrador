package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteInfoDTO;
import com.example.EcomerceUribe.modelos.mapas.IClienteMapa;
import com.example.EcomerceUribe.repositorios.IClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteServicio {

    @Autowired
    private IClienteRepositorio repositorio;

    @Autowired
    private IClienteMapa mapa;

    public ClienteInfoDTO guardarCliente(Cliente datosCliente) {
        // Validación: el nombre no puede estar vacío
        if (datosCliente.getNombre() == null || datosCliente.getNombre().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El nombre del cliente es obligatorio"
            );
        }

        Cliente clienteGuardado = this.repositorio.save(datosCliente);
        return this.mapa.convertir_cliente_a_clientedto(clienteGuardado);
    }
}
