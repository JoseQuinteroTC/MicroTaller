package com.TallerApi.demo.dto.pedido;

import java.time.LocalDate;

public record PedidoToSaveDto(LocalDate fechaPedido,
                              Long idCliente,
                              String status) {
}
