package com.TallerApi.demo.dto.detallesEnvios;

public record DetallesEnviosToSaveDto(String direccion,
                                      String transportadora,
                                      Integer numeroGuia) {
}
