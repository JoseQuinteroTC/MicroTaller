package com.TallerApi.demo.dto.detalleEnvio;

import com.TallerApi.demo.model.enums.EstadoEnvio;

public record DetalleEnvioActualizarDto(
        String transportadora,
        String direccion,
        EstadoEnvio estadoEnvio
) {
}
