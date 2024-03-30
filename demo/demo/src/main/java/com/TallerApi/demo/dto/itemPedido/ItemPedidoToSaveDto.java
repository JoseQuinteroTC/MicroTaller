package com.TallerApi.demo.dto.itemPedido;

public record ItemPedidoToSaveDto(Integer cantidad,
                                  Long idProducto,
                                  Integer precioUnitario) {
}
