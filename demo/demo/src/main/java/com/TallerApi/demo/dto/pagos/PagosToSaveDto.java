package com.TallerApi.demo.dto.pagos;

import com.TallerApi.demo.models.enums.MetodoDePago;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PagosToSaveDto(Integer totalPago,

                             LocalDateTime fechaPago,
                             Long idPedido,
                             MetodoDePago metodoPago) {
}
