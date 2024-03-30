package com.TallerApi.demo.service.detalleEnvio;

import com.TallerApi.demo.dto.detalleEnvio.DetalleEnvioActualizarDto;
import com.TallerApi.demo.dto.detalleEnvio.DetalleEnvioDto;
import com.TallerApi.demo.dto.detalleEnvio.DetalleEnvioToSaveDto;
//import com.TallerApi.demo.dto.detalleEnvio.DetalleEnvioUpdateDto;
import com.TallerApi.demo.model.enums.EstadoEnvio;

import java.util.List;

public interface DetalleEnvioService {
    DetalleEnvioDto crearDetalleEnvio(DetalleEnvioToSaveDto detalleEnvioToSaveDto);

    DetalleEnvioDto actualizarDetalleEnvio(Long id, DetalleEnvioActualizarDto detalleEnvioActualizarDto);

    DetalleEnvioDto buscarDetalleEnvioById(Long id);

    void removerDetalleEnvio(Long id);

    List<DetalleEnvioDto> getAllDetalleEnvio();

    DetalleEnvioDto getDetalleEnvioByPedidoId(Long id);

    List<DetalleEnvioDto> getDetalleEnviosByTransportadora(String transportadora);

    List<DetalleEnvioDto> getDetalleEnviosByEstado(EstadoEnvio estadoEnvio);
}
