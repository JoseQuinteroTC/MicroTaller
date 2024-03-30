package com.TallerApi.demo.dto.detalleEnvio;

import com.TallerApi.demo.model.enums.EstadoEnvio;

public record DetalleEnvioDto(Long id,
                              String direccion,
                              EstadoEnvio estadoEnvio,
                              String transportadora,
                              String numeroGuia,
                              Long idPedido) {
}
