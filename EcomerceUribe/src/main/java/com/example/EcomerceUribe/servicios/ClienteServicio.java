package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.Cliente;
import com.example.EcomerceUribe.modelos.DTOS.ClienteInfoDTO;
import com.example.EcomerceUribe.modelos.mapas.IClienteMapa;
import com.example.EcomerceUribe.repositorios.IClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    // Buscar todos
    public List<ClienteInfoDTO> buscarTodos() {
        return mapa.convertir_lista_a_lista_clienteinfodto(repositorio.findAll());
    }

    // Buscar por id
    public ClienteInfoDTO buscarPorId(Integer id) {
        Optional<Cliente> cliente = repositorio.findById(id);
        if (!cliente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el cliente con id " + id);
        }
        return mapa.convertir_cliente_a_clientedto(cliente.get());
    }

    // Eliminar
    public void eliminar(Integer id) {
        Optional<Cliente> cliente = repositorio.findById(id);
        if (!cliente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el cliente con id " + id);
        }
        repositorio.delete(cliente.get());
    }

    // Actualizar dirección y calificación
    public ClienteInfoDTO actualizar(Integer id, Cliente nuevosDatos) {
        Cliente cliente = repositorio.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el cliente con id " + id)
        );
        cliente.setDireccion(nuevosDatos.getDireccion());
        cliente.setCalificacion(nuevosDatos.getCalificacion());

        return mapa.convertir_cliente_a_clientedto(repositorio.save(cliente));
    }
}
