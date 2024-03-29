package com.TallerApi.demo.dto.detallesEnvios;

import com.TallerApi.demo.models.DetallesEnvios;

public interface DetallesEnviosMapper {

    DetallesEnviosDto detallesEnviosToDetallesEnviosDto (DetallesEnvios detallesEnvios);

    DetallesEnvios detallesEnviosDtoToDetallesEnvios(DetallesEnviosDto detallesEnviosDto);

    DetallesEnvios detallesEnviosToSaveDtoToDetallesEnvios(DetallesEnviosToSaveDto detallesEnviosToSaveDto);
}
