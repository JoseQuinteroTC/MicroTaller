package com.TallerApi.demo.dto.pedido;

import com.TallerApi.demo.model.enums.EstadoPedido;

public record PedidoActualizarDto(
        EstadoPedido estadoPedido
) {
}
