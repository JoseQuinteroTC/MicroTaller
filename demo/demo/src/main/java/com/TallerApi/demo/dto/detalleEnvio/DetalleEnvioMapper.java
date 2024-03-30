package com.TallerApi.demo.dto.detalleEnvio;

import com.TallerApi.demo.model.DetalleEnvio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetalleEnvioMapper {
    @Mapping(source = "pedido.id", target = "idPedido")
    DetalleEnvioDto detalleEnvioToDetalleEnvioDto (DetalleEnvio detalleEnvio);
    DetalleEnvio detalleEnvioToSaveDtoToDetalleEnvio(DetalleEnvioToSaveDto detalleEnvioToSaveDto);
}
