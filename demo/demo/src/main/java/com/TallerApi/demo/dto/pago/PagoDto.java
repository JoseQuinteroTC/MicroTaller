package com.TallerApi.demo.dto.pago;

import java.time.LocalDateTime;

public record PagoDto(Long id,

                      Long idPedido,
                      Integer totalPago,
                      LocalDateTime fechaPago,
                      String metodoPago
                       ) {
}

