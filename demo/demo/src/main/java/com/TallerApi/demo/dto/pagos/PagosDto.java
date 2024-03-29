package com.TallerApi.demo.dto.pagos;

import com.TallerApi.demo.models.Pedidos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PagosDto(Long id,

                       Long idPedido,
                       Integer totalPago,
                       LocalDateTime fechaPago,
                       String metodoPago
                       ) {
}

