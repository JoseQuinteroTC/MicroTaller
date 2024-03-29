package com.TallerApi.demo.dto.products;

import com.TallerApi.demo.dto.detallesEnvios.DetallesEnviosDto;
import com.TallerApi.demo.dto.detallesEnvios.DetallesEnviosToSaveDto;
import com.TallerApi.demo.models.DetallesEnvios;

public interface ProductsMapper {
    DetallesEnviosDto detallesEnviosToDetallesEnviosDto (DetallesEnvios detallesEnvios);

    DetallesEnvios detallesEnviosDtoToDetallesEnvios(DetallesEnviosDto detallesEnviosDto);

    DetallesEnvios detallesEnviosToSaveDtoToDetallesEnvios(DetallesEnviosToSaveDto detallesEnviosToSaveDto);
}
