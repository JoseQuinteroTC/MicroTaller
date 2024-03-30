package com.TallerApi.demo.dto.itemPedido;

public record ItemPedidoDto(Long id,
                            Long pedidoId,
                            Long productoId,
                            Integer cantidad,
                            Integer precioUnitario) {
}

