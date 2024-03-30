package com.TallerApi.demo.service.pago;

import com.TallerApi.demo.dto.pago.PagoDto;
import com.TallerApi.demo.dto.pago.PagoToSaveDto;
import com.TallerApi.demo.model.enums.MetodoDePago;

import java.util.List;

public interface PagoService {
    PagoDto crearPago (PagoToSaveDto pagoToSaveDto);

    PagoDto actualizarPago (Long id, PagoToSaveDto pagoToSaveDto);

    PagoDto buscarPorId (Long id);

    void eliminarPago(Long id);

    List<PagoDto> mostrarTodos ();

    List<PagoDto> mostrarPagosEntreFechas (String initDate, String endDate);

    List<PagoDto> mostrarPorIdOrdenAndMetodoPago (Long idOrden, MetodoDePago metodoDePago);



}
