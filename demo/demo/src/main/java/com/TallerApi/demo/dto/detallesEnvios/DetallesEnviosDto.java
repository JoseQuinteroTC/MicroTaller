package com.TallerApi.demo.dto.detallesEnvios;

public record DetallesEnviosDto(Long id,
                                Long pedidoId,
                                String direccion,
                                String transportadora,
                                Integer numeroGuia) {
}
