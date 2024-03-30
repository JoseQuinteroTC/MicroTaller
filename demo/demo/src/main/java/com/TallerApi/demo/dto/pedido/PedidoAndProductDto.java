package com.TallerApi.demo.dto.pedido;

import com.TallerApi.demo.dto.itemPedido.ItemPedidoDto;
import com.TallerApi.demo.model.enums.EstadoPedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoAndProductDto(
        Long id,
        Long clienteId,
        Long pagoId,
        LocalDateTime fechaPedido,
        EstadoPedido estadoPedido,
        Long idDetalleEnvio,
        List<ItemPedidoDto> itemsPedido
) {
}
