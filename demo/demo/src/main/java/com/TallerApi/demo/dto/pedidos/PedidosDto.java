package com.TallerApi.demo.dto.pedidos;

import java.time.LocalDate;

public record PedidosDto(Long id,
                         Long clienteId,
                         LocalDate fechaPedido,
                         String status) {
}
