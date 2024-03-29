package com.TallerApi.demo.dto.pedidos;

import java.time.LocalDate;

public record PedidosToSaveDto(LocalDate fechaPedido,
                               String status) {
}
