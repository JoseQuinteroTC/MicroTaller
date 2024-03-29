package com.TallerApi.demo.dto.itemsPedidos;

import com.TallerApi.demo.dto.detallesEnvios.DetallesEnviosDto;
import com.TallerApi.demo.dto.detallesEnvios.DetallesEnviosToSaveDto;
import com.TallerApi.demo.models.DetallesEnvios;
import com.TallerApi.demo.models.ItemsPedidos;

public interface ItemsPedidosMapper {

    ItemsPedidosDto itemsPedidosToItemsPedidosDto (ItemsPedidos itemsPedidos);

    ItemsPedidos itemsPedidosDtoToItemsPedidos(ItemsPedidosDto itemsPedidosDto);

    ItemsPedidos itemsPedidosToSaveDtoToItemsPedidos(ItemsPedidosToSaveDto pedidosToSaveDto);
}
