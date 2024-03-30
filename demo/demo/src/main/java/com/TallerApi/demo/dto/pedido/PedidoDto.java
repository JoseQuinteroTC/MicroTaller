package com.TallerApi.demo.dto.pedido;

import java.time.LocalDate;

public record PedidoDto(Long id,
                        Long clienteId,
                        LocalDate fechaPedido,
                        String status) {
}
