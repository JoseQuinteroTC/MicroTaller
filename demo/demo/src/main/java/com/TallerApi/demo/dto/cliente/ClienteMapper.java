package com.TallerApi.demo.dto.cliente;

import com.TallerApi.demo.model.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDto clienteToClienteDto(Cliente cliente);


    Cliente clienteToSaveDtoToCliente(ClienteToSaveDto clienteToSaveDto);
}
