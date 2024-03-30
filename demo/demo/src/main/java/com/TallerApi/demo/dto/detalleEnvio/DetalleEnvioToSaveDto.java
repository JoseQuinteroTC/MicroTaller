package com.TallerApi.demo.dto.detalleEnvio;

public record DetalleEnvioToSaveDto(String direccion,
                                    Long idPedido,
                                    String transportadora) {
}
