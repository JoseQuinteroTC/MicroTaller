package com.TallerApi.demo.service.clientes;

import com.TallerApi.demo.dto.clientes.ClientesDto;
import com.TallerApi.demo.dto.clientes.ClientesToSaveDto;

public interface ClienteService {
    ClientesDto crearCliente (ClientesToSaveDto clientesToSaveDto);

    ClientesDto actualizarCliente (ClientesToSaveDto clientesToSaveDto);

    ClientesDto buscarPorId (Long id);

    void eliminarProducto(Long id);


}
