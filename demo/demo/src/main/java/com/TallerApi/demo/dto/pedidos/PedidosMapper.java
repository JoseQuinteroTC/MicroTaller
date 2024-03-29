package com.TallerApi.demo.dto.pedidos;

import com.TallerApi.demo.dto.detallesEnvios.DetallesEnviosDto;
import com.TallerApi.demo.dto.detallesEnvios.DetallesEnviosToSaveDto;
import com.TallerApi.demo.models.DetallesEnvios;
import com.TallerApi.demo.models.Pedidos;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PedidosMapper {
    PedidosDto pedidosToPedidosDto (Pedidos pedidos);


    Pedidos pedidosDtoToPedidos(PedidosDto pedidosDto);

    Pedidos pedidosToSaveDtoToPedidos(PedidosToSaveDto pedidosToSaveDto);
}
