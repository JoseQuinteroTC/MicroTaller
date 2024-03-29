package com.TallerApi.demo.service.pagos;

import com.TallerApi.demo.dto.clientes.ClientesDto;
import com.TallerApi.demo.dto.clientes.ClientesToSaveDto;
import com.TallerApi.demo.dto.pagos.PagosDto;
import com.TallerApi.demo.dto.pagos.PagosToSaveDto;
import com.TallerApi.demo.dto.pedidos.PedidosDto;
import com.TallerApi.demo.dto.pedidos.PedidosToSaveDto;
import com.TallerApi.demo.models.enums.MetodoDePago;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PagosService {
    PagosDto crearPago (PagosToSaveDto pagosToSaveDto);

    PagosDto actualizarPago (Long id,PagosToSaveDto pagosToSaveDto);

    PagosDto buscarPorId (Long id);

    void eliminarPago(Long id);

    List<PagosDto> mostrarTodos ();

    List<PagosDto> mostrarPagosEntreFechas (String initDate, String endDate);

    List<PagosDto> mostrarPorIdOrdenAndMetodoPago (Long idOrden, MetodoDePago metodoDePago);



}
