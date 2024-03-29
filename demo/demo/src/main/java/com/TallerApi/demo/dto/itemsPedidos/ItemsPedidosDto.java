package com.TallerApi.demo.dto.itemsPedidos;

public record ItemsPedidosDto(Long id,
                              Long pedidoId,
                              Long productoId,
                              Integer cantidad,
                              Integer precioUnitario) {
}

