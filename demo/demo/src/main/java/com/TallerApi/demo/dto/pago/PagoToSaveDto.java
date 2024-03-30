package com.TallerApi.demo.dto.pago;

import com.TallerApi.demo.model.enums.MetodoDePago;

import java.time.LocalDateTime;

public record PagoToSaveDto(Integer totalPago,

                            LocalDateTime fechaPago,
                            Long idPedido,
                            MetodoDePago metodoPago) {
}
