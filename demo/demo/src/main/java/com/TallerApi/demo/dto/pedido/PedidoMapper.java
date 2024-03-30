package com.TallerApi.demo.dto.pedido;

import com.TallerApi.demo.model.Pedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    PedidoDto pedidoToPedidoDto (Pedido pedido);


    Pedido pedidoDtoToPedido(PedidoDto pedidoDto);

    Pedido pedidoToSaveDtoToPedido(PedidoToSaveDto pedidoToSaveDto);
}
