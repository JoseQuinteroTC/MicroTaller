package com.TallerApi.demo.service.cliente;

import com.TallerApi.demo.dto.cliente.ClienteDto;
import com.TallerApi.demo.dto.cliente.ClienteToSaveDto;

import java.util.List;

public interface ClienteService {

    ClienteDto crearCliente(ClienteToSaveDto clienteToSaveDto);

    ClienteDto actualizarCliente(Long id, ClienteToSaveDto clienteToSaveDto);

    ClienteDto buscarClienteById(Long id);

    void removerCliente(Long id);

    List<ClienteDto> getAllClientes();

    ClienteDto buscarClienteByEmail(String email);

    ClienteDto buscarClienteByDireccion(String direccion);

    List<ClienteDto> buscarClientesNombreEmpiezaPor(String nombre);

}
