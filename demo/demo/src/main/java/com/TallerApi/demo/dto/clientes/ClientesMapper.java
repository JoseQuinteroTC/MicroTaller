package com.TallerApi.demo.dto.clientes;

import com.TallerApi.demo.models.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientesMapper {

    ClientesDto clientesToClientesDto(Cliente cliente);
    Cliente clientesDtoToClientes(ClientesDto clientesDto);

    Cliente clientesToSaveDtoToClientes(ClientesToSaveDto clientesToSaveDto);
}
