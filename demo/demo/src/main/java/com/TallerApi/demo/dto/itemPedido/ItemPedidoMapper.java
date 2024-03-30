package com.TallerApi.demo.dto.itemPedido;

import com.TallerApi.demo.dto.pedido.PedidoMapper;
import com.TallerApi.demo.model.ItemPedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {

    ItemPedidoDto itemPedidoToItemPedidoDto (ItemPedido itemPedido);

    ItemPedido itemPedidoDtoToItemPedido(ItemPedidoDto itemPedidoDto);

    ItemPedido itemPedidoToSaveDtoToItemPedido(ItemPedidoToSaveDto pedidosToSaveDto);
}
